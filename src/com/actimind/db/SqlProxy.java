package com.actimind.db;

import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqlProxy allows to create dynamically a db accessor class from the interface with @Sql method annotations.
 * When method of generated instance is called then the sql command specified in @Sql annotation will be executed.
 * <p/>
 * For example:
 * <p/>
 * <code>
 * interface CatsDB {
 *
 *     @Sql("insert into cats :insert")
 *     public void addCat(Cat cat);
 *
 *     @Sql("select * from cats where id = :1")
 *     public Cat findCat(int id);
 *
 * }
 * </code>
 *
 * <p/>
 * Note that it opens new connection on each call
 * <p/>
 *
 * == Mapper objects
 *
 * In order to correctly process complex objects there is a {@link Mapper} interface. Each complex class shall have its own Mapper implementation
 * and shall register it to the {@link Mapper.Registry} class.
 *
 * <code>
 * class CatMapper implements Mapper<Cat> {
 *
 *      public Cat produceFrom(ResultSet result) throws SQLException {
 *       Cat cat = new Cat();
 *       stat.setName(result.getString("name"));
 *       return cat;
 *   }
 *
 *   public Map<String, Object> toMap(Cat object) {
 *       Map<String, Object> map = new HashMap<String, Object>();
 *       map.put("name", object.getName());
 *       return map;
 *   }
 * }
 * </code>
 *
 * == Params mapping
 *
 * If method has args, then all of them will be substituted to provided sql command to the places marked with ":<nr>", for example:
 *
 *   @Sql("select count(*) from cats where name like :1")
 *   public int findCatsCount(String name);
 *
 * If argument is a complex object which has a Mapper then any of its attributes may be substituted the following way:
 *
 *   @Sql("select count(*) from cats where name like :name")
 *   public int findCatsSameName(Cat cat);
 *
 * == Result mapping
 *
 * If sql result is a simple object (number, string) then it will be just returned (in case the return type of method is specified correctly)
 *
 * If method's return type is a complex object with Mapper then result set will be converted to this object using Mapper.
 *
 * So you may specify the following return types of sql methods:
 *   1. Simple type
 *
 *   @Sql("select name from cats where id = :1")
 *   String getName(int id);
 *
 *   2. Complex type
 *
 *   @Sql("select * from cats where id = :1")
 *   Cat getCat(int id);
 *
 *   3. Array of simple of complex types
 *
 *   @Sql("select * from cats")
 *   Cat[] getCat(int id);
 *
 * You shall use exactly arrays, not collections. Otherwise dynamic proxy cannot detect objects of which classes shall be put into collection
 * as information about generics is not available in runtime.
 *
 * == Additional abilities
 *
 * In order to simplity insert operation the :insert pseudo-parameter is replaced with correct list of all attributes and their values of specified object
 *
 *    @Sql("insert into cats :insert")
 *    public void newCat(Cat cat);
 *
 */
public class SqlProxy implements InvocationHandler {

    private Connector connector;
    static Logger logger = Logger.getLogger("DB");
    private SQLExceptionHandler handler = SQLITE;

    public SqlProxy(Connector connector) {
        this.connector = connector;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        handler.beforeStart();
        if (method.getAnnotation(Sql.class) != null) {
            return sql(method, args, method.getAnnotation(Sql.class));
        }
        return null;
    }

    private Object sql(Method method, Object[] args, Sql annotation) throws Exception {
        Connection c = connector.connect();
        try {
            try {
                PreparedStatement st = statement(args, annotation, c);
                Class ret = method.getReturnType();
                if (ret != Void.TYPE) {
                    return select(st, ret);
                }
                else {
                    update(st);
                    return null;
                }
            }
            finally {
                c.close();
            }
        }
        catch (SQLException e) {
            //connection is closed here
            if (handler.repeat(e))
                return sql(method, args, annotation);
            throw e;
        }

    }

    private void update(PreparedStatement st) throws SQLException {
        st.executeUpdate();
    }

    private Object select(PreparedStatement st, Class ret) throws SQLException {
        ResultSet result = st.executeQuery();
        boolean castToArray = false;
        if (ret.isArray()) {
            ret = ret.getComponentType();
            castToArray = true;
        }
        Mapper retMapper = Mapper.Registry.get(ret);
        if (retMapper == null) {
            retMapper = new CastMapper();
        }
        List resultList = new ArrayList();
        while (result.next()) {
            resultList.add(retMapper.produceFrom(result));
        }

        if (resultList.isEmpty()) {
            if (castToArray) return Array.newInstance(ret, 0); else return null;
        }
        else {
            if (castToArray) {
                Object[] array = (Object[]) Array.newInstance(ret, resultList.size());
                int i = 0;
                for (Object o: resultList) {
                    array[i] = o;
                    i++;
                }
                return array;
            }
            else
                return resultList.get(0);
        }
    }

    private PreparedStatement statement(Object[] args, Sql annotation, Connection c) throws SQLException {
        logger.info("Start prepare statement");
        Map<String, Object> queryParams = new HashMap<String, Object>();
        int argNr = 1;
        if (args != null) {
            for (Object arg: args) {
                Mapper mapper = Mapper.Registry.get(arg.getClass());
                Map map = null;
                if (mapper != null) {
                    map = mapper.toMap(arg);
                }
                if (map != null) {
                    queryParams.putAll(mapper.toMap(arg));
                }
                else {
                    queryParams.put(argNr+"", arg);
                }
                argNr++;
            }
        }
        PreparedStatement st = PreparedStatementEx.prepare(c, annotation.value(), queryParams);
        return st;
    }

    /**
     * Produces sql accessor
     * @param iface interface with methods marked using @Sql annotation
     * @param connector database connector
     * @param <T>
     * @return sql accessor implementing specified interface
     */
    public static <T> T produceSqlFacade(Class<T> iface, Connector connector) {
        return (T)Proxy.newProxyInstance(iface.getClassLoader(), new Class[] {iface}, new SqlProxy(connector));
    }

    static interface SQLExceptionHandler {
        boolean repeat(SQLException e) throws Exception;

        void beforeStart();
    }

    private static final int MAX_RETRIES = 15;

    public static SQLExceptionHandler SQLITE = new SQLExceptionHandler() {
        private int repetitionsCount = 0;

        public boolean repeat(SQLException e) throws Exception {
            boolean locked = e.getMessage().toLowerCase().matches(".*database.*locked.*");
            if (locked) {
                if (repetitionsCount > MAX_RETRIES) {
                    logger.warn("DB is locked, throw exception");
                    throw e;
                }
                repetitionsCount++;
                logger.info("DB is locked, retry, attempt#" + repetitionsCount);
                Thread.sleep(100*repetitionsCount);
            }
            return locked;
        }

        public void beforeStart() {
            repetitionsCount = 0;
        }
    };

    public static SQLExceptionHandler DEFAULT = new SQLExceptionHandler() {
        public boolean repeat(SQLException e) {
            return false;
        }

        public void beforeStart() {

        }
    };
}
