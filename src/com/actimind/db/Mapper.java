package com.actimind.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @param <T>
 */
public interface Mapper<T> {

    T produceFrom(ResultSet result) throws SQLException;

    Map<String, Object> toMap(T object);

    static Map<Class, Mapper> mappers = new HashMap<Class, Mapper>();

    public class Registry {
        public static void addMapper(Class c, Mapper m) {
            mappers.put(c, m);
        }

        public static Mapper get(Class c) {
            return mappers.get(c);
        }

        static {
            mappers.put(Boolean.class, new BooleanMapper());
        }
    }


    
}
