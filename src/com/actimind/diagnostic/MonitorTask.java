package com.actimind.diagnostic;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

class MonitorTask extends TimerTask {

    private Logger logger = Logger.getLogger(getClass());
    private MonitorTaskConfig config;

    public interface Listener {
        public void onUpdate();
    }

    private List<Listener> listeners = new ArrayList<Listener>();

    public MonitorTask(MonitorTaskConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
        logger.debug("Start collecting");
        try {
            boolean force = config.isForce();
            for (SingleStatProcessor processor: config.getProcessors()) {
                try {
                    processor.checkDateAndGetStat(force);
                }
                catch (Exception e) {
                    logger.error("Error occurring during collecting the " + processor.getName(), e);
                }
            }
            config.resetForce();
            onUpdate();
            logger.debug("Finish collecting");
        }
        catch (Exception e) {
            logger.error("Unexpected error. Wait 10 seconds before continue", e);
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            }
            catch (InterruptedException ignore) {}
        }
    }

    private void onUpdate() {
        for (Listener l: listeners) {
            l.onUpdate();
        }
    }

    public void addListener(Listener l) {
        listeners.add(l);
    }
}
