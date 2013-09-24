package com.actimind.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CastMapper<T> implements Mapper<T> {
    public T produceFrom(ResultSet result) throws SQLException {
        return (T)result.getObject(1);
    }

    public Map<String, Object> toMap(T object) {
        return null;
    }
}
