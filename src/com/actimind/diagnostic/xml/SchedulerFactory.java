package com.actimind.diagnostic.xml;

import com.actimind.diagnostic.date.DateParser;
import com.actimind.diagnostic.date.Scheduler;
import org.apache.log4j.Logger;

import java.util.List;

class SchedulerFactory {

    private Logger logger = Logger.getLogger(getClass());

    private List<DateParser> parsers;

    public SchedulerFactory(List<DateParser> parsers) {
        this.parsers = parsers;
    }

    public Scheduler getScheduler(String every) {
        logger.info("   Find scheduler for: " + every);
        for (DateParser p: parsers) {
            Scheduler s = p.parse(every);
            if (s != null) {
                logger.info("   Found: " + s);
                return s;
            }
        }
        logger.info("   Nothing found");
        return null;
    }
}
