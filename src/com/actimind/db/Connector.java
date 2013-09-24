package com.actimind.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Allows to create connection to DB.
 * Do not forget to close your connections!
 */
public class Connector {

    private String url;

    public Connector(String url) {
        this.url = url;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

}
