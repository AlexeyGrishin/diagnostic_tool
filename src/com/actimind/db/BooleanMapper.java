package com.actimind.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Mapper which knows about SQLite specific boolean handling
 */
public class BooleanMapper implements Mapper<Boolean> {

    private final static String T = Boolean.TRUE.toString();
    private final static String F = Boolean.FALSE.toString();

    public Boolean produceFrom(ResultSet result) throws SQLException {
        String s = result.getString(1);
        if (s.equalsIgnoreCase(T))
            return true;
        if (s.equalsIgnoreCase(F))
            return false;
        int i = result.getInt(1);
        if (i == 1) return true;
        if (i == 0) return false;
        return result.getBoolean(1);
    }

    public Map<String, Object> toMap(Boolean object) {
        return null;
    }
}
