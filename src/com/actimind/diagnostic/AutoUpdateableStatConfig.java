package com.actimind.diagnostic;

import com.actimind.diagnostic.db.StatDB;
import com.actimind.diagnostic.listeners.StatProcessorListener;
import com.actimind.diagnostic.xml.ListenersFactory;
import com.actimind.diagnostic.xml.SingleStatProcessorFactory;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXB;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class AutoUpdateableStatConfig implements MonitorTaskConfig {

    long lastUpdate = 0;
    private StatDB db;
    private File xml;
    SingleStatProcessorFactory factory;
    List<SingleStatProcessor> processors;
    private Logger logger = Logger.getLogger(getClass());

    AutoUpdateableStatConfig(File xml, StatDB db, SingleStatProcessorFactory factory) throws Exception {
        this.xml = xml;
        this.db = db;
        this.factory = factory;
        init();
    }

    public boolean isForce() {
        return db.isForceEnabled();
    }

    public Collection<SingleStatProcessor> getProcessors() {
        if ( lastUpdate != xml.lastModified()) {
            logger.info("XML file change detected");
            try {
                init();
                lastUpdate = xml.lastModified();
            }
            catch (Exception e) {
                logger.info("Cannot load updated XML file", e);
            }
        }
        return processors;
    }

    public void resetForce() {
        db.updateForce(0);
    }

    public void init() throws Exception {
        com.actimind.diagnostic.xml.Monitor m = JAXB.unmarshal(xml, com.actimind.diagnostic.xml.Monitor.class);
        processors = factory.produce(m);
        initListeners(m, processors, db);
    }

    private void initListeners(com.actimind.diagnostic.xml.Monitor m, List<SingleStatProcessor> processors, StatDB db) throws Exception, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ListenersFactory lf = new ListenersFactory();
        List<StatProcessorListener> listeners = new ArrayList<StatProcessorListener>();
        listeners.add(new DBSaver(db));
        listeners.add(new ErrorLogger());
        lf.fillListeners(m, listeners, new SingleStatProcessorFactory().obtainGlobalParams(m));
        for (SingleStatProcessor p: processors) {
            for (StatProcessorListener listener: listeners) {
                p.addListener(listener);
            }
        }
    }
}
