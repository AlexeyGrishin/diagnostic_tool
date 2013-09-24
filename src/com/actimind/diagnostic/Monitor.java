package com.actimind.diagnostic;

import com.actimind.db.Connector;
import com.actimind.db.SqlProxy;
import com.actimind.diagnostic.db.StatDB;
import com.actimind.diagnostic.db.StatMapper;
import com.actimind.diagnostic.xml.SingleStatProcessorFactory;
import org.apache.log4j.BasicConfigurator;

import javax.xml.bind.JAXB;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Monitor {
    private static final long DELAY = TimeUnit.SECONDS.toMillis(3);


    static {
        BasicConfigurator.configure();
        StatMapper.init();
    }

    public Monitor(String xmlPath, String dbUrl) throws Exception, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        File xml = new File(xmlPath);
        if (!xml.exists()) throw new IllegalArgumentException("XML file with monitor description is not found");

        SingleStatProcessorFactory factory = new SingleStatProcessorFactory();
        Connector conn = new Connector(dbUrl);
        final StatDB db = SqlProxy.produceSqlFacade(StatDB.class, conn);
        AutoUpdateableStatConfig config = new AutoUpdateableStatConfig(xml, db, factory);
        MonitorTask task = new MonitorTask(config);
        Timer t = new Timer();
        t.schedule(task, new Date(), DELAY);

    }


    public static void main(String args[]) throws Exception, CannotProduceDateParser, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String xmlPath = "monitor.xml";
        if (args.length > 0) xmlPath = args[0];
        xmlPath = new File(xmlPath).getAbsolutePath();
        com.actimind.diagnostic.xml.Monitor m = JAXB.unmarshal(xmlPath, com.actimind.diagnostic.xml.Monitor.class);
        String dbName = null;
        for (com.actimind.diagnostic.xml.Monitor.Use use: m.getUse()) {
            if (use.getDb() != null) {
                dbName = use.getDb();
                Class.forName(use.getConnector());
            }
        }
        if (dbName == null) throw new RuntimeException("Database info shall be provided in 'use' tag in monitor.xml, attributes 'db' and 'connector'");
        new Monitor(xmlPath, dbName);
    }

}
