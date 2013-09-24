package com.actimind.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PreparedStatementEx {

    static Logger logger = Logger.getLogger(PreparedStatementEx.class);

    /**
     * Prepares statement based on sql with named parameters inside
     * using parameter values specified in map.
     * 
     * Example:
     *  Map<String, Object> map = new Map<~>;
     *  map.put("person.name", "Alex");
     *  map.put("person.age", 11);
     * 
     *  PreparedStatementEx.prepare("select count(*) from Person where name=:person.name and age=:person.age", map);
     *
     * a :map placeholder is special one - the whole params map is subbsituted in form name1=value1,name2=value2
     *
     * @param con
     * @param sql
     * @param params
     * @return
     */
    public static PreparedStatement prepare(Connection con, String sql, Map<String, Object> params) throws SQLException {
        List<String> names = new ArrayList<String>();
        String preparedSql = prepareSql(preprocessSql(sql, params), names);
        PreparedStatement statement = con.prepareStatement(preparedSql);
        for (int i = 0; i < names.size(); i++) {
            statement.setObject(i+1, params.get(names.get(i)));
        }
        return statement;
    }

    private static String preprocessSql(String sql, Map<String, Object> params) {
        if (sql.contains(":map")) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (String key: params.keySet()) {
                if (!first) sb.append(", ");
                sb.append(key).append(" = ").append(":").append(key).append(" ");
                first = false;
            }
            return sql.replace(":map", sb.toString());
        }
        if (sql.contains(":insert")) {
            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder();
            boolean first = true;
            for (String key: params.keySet()) {
                if (!first) {
                    names.append(", ");
                    values.append(", ");
                }
                names.append("`").append(key).append("` ");
                values.append(":").append(key).append(" ");
                first = false;
            }
            return sql.replace(":insert", "(" + names + ") values (" + values + ")");

        }
        return sql;
    }

    private static String prepareSql(String sql, List<String> names) {
        logger.info("Prepare sql: " + sql);
        String parts[] = sql.split(":");
        StringBuilder bld = new StringBuilder();
        boolean first = true;
        for (String part: parts) {
            if (first) {
                first = false;
                bld.append(part);
            }
            else {
                bld.append("?");
                int space = part.indexOf(' ');
                if (space > -1) {
                    names.add(part.substring(0, space));
                    bld.append(part.substring(space));
                }
                else {
                    names.add(part);
                }
            }
        }
        logger.info("Prepared sql: " + bld.toString());
        logger.info("Found names: " + names.toString());
        return bld.toString();
    }
    
    
    public static class SqlBuilder {
        private Connection con;
        private String sql;
        private Map<String, Object> params = new HashMap<String, Object>();

        public SqlBuilder(Connection con, String sql) {
            this.con = con;
            this.sql = sql;
        }
        
        public SqlBuilder with(String name, Object value) {
            params.put(name, value);
            return this;
        }
        
        public PreparedStatement toStatement() throws SQLException {
            return prepare(con, sql, params);
        }
    }
    
    public static SqlBuilder prepare(Connection con, String sql) {
        return new SqlBuilder(con, sql);
    }
    
}
