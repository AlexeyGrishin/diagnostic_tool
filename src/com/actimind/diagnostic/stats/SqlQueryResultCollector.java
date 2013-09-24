package com.actimind.diagnostic.stats;

import com.actimind.db.Connector;
import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

@TextPattern("Result of SQL query ':param'")
@AttributePattern("sql")
public class SqlQueryResultCollector implements StatCollector, ParamsAware {

    private String query;
    private Connector con;

    public SqlQueryResultCollector(String query) {
        this.query = query;
    }

    public Object getStat() throws Exception {
        Connection c = con.connect();
        try {
            ResultSet set = c.prepareStatement(query).executeQuery();
            if (set.next()) {
                return set.getObject(1);
            }
            else {
                return "<Empty Query Result>";
            }
        }
        finally {
            c.close();
        }
    }

    public void setParams(Map<String, String> global)  throws Exception {
        Class.forName(global.get("connector-class"));
        con = new Connector(global.get("db-url"));
    }
}
