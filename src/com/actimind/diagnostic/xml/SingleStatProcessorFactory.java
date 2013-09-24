package com.actimind.diagnostic.xml;

import com.actimind.common.ClassEx;
import com.actimind.diagnostic.CannotProduceDateParser;
import com.actimind.diagnostic.CannotProduceStatCollector;
import com.actimind.diagnostic.SingleStatProcessor;
import com.actimind.diagnostic.date.DateParser;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SingleStatProcessorFactory {

    private Logger logger = Logger.getLogger(getClass());
    private GlobalParams global;

    public List<SingleStatProcessor> produce(Monitor m) throws CannotProduceStatCollector,
            CannotProduceDateParser {
        List<SingleStatProcessor> processors = new ArrayList<SingleStatProcessor>();
        List<DateParser> parsers = obtainDateParsers(m);
        SchedulerFactory sf = new SchedulerFactory(parsers);
        obtainGlobalParams(m);

        new NewestFactory(global, sf).fill(m, processors);

        return processors;

    }

    public GlobalParams obtainGlobalParams(Monitor m) {
        global = new GlobalParams();
        if (m.getGlobal() == null) return global;
        for (ParamsType p: m.getGlobal().getParam()) {
            global.put(p.getName(), p.getValue());
        }
        return global;
    }



    private List<DateParser> obtainDateParsers(Monitor m) throws CannotProduceDateParser {
        List<DateParser> parsers = new ArrayList<DateParser>();
        try {
            for (String p: m.getDateParsers().getParser()) {
                parsers.add(ClassEx.createInstance(DateParser.class, p));
            }
        }
        catch (Exception e) {
            throw new CannotProduceDateParser(e);
        }
        return parsers;
    }

}
