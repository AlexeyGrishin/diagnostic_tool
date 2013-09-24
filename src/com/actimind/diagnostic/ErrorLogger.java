package com.actimind.diagnostic;

import com.actimind.diagnostic.db.Stat;
import com.actimind.diagnostic.listeners.StatProcessorListener;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.SimpleLayout;

import java.io.IOException;

class ErrorLogger implements StatProcessorListener {

    private Logger logger = Logger.getLogger("MONITOR");

    ErrorLogger() {
        try {
            logger.addAppender(new RollingFileAppender(
                    new SimpleLayout(), "monitor.warnings",
                    true
            ));
        } catch (IOException e) {
            logger.error("Cannot configure logger", e);
        }
    }

    public void onNewStatCollected(Stat stat) {
        //nothing
    }

    public void onStatStateChanged(Stat stat) {
        if (stat.isNormalState())
            logger.info(stat.getName() + " now is in normal state: " + stat.getStoredValueAsString());
        else
            logger.error(stat.getName() + ": " + stat.getStateDescription() + ": " + stat.getStoredValueAsString());
    }

    public void onFirstStat(Stat stat) {
        onStatStateChanged(stat);
    }

    public void onStatValueChanged(Stat stat) {
        
    }
}
