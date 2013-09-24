package com.actimind.diagnostic.xml;

import com.actimind.common.ClassEx;
import com.actimind.diagnostic.CannotProduceStatCollector;
import com.actimind.diagnostic.SingleStatProcessor;
import com.actimind.diagnostic.StatProducer;
import com.actimind.diagnostic.date.Scheduler;
import com.actimind.diagnostic.stats.StatCollector;
import com.actimind.diagnostic.validator.StatValidator;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewestFactory {

    private GlobalParams params;
    private SchedulerFactory parsers;
    private Logger logger = Logger.getLogger(getClass());
    private Map<String, Class> classMap = new HashMap<String, Class>();

    public NewestFactory(GlobalParams params, SchedulerFactory parsers) {
        this.params = params;
        this.parsers = parsers;
    }

    public void fill(Monitor m, List<SingleStatProcessor> processors) throws CannotProduceStatCollector {
        parseUsages(m.getUse());
        for (Monitor.Stats.Do action: m.getStats().getDo()) {
            StatCollector collector = null;
            List<StatValidator> validators = new ArrayList<StatValidator>();
            Scheduler scheduler = null;
            Map<QName, String> attributes = action.getOtherAttributes();
            String collName = extractCollName(attributes);
            for (Map.Entry<QName, String> attr: attributes.entrySet()) {
                String name = attr.getKey().getLocalPart();
                if (name.equals("at") || name.equals("every")) {
                    scheduler = parsers.getScheduler(attr.getValue());
                }
                else if (classMap.containsKey(name)) {
                    try {
                        Object o = params.withLocalParams(action.getParam()).apply(classMap.get(name).getConstructor(String.class).newInstance(attr.getValue()));
                        if (o instanceof StatCollector) {
                            collector = (StatCollector) o;
                            if (collName == null) collName = name.replaceAll("-", " ") + " " + attr.getValue();
                        }
                        else {
                            validators.add((StatValidator) o);
                        }
                    } catch (Exception e) {
                        throw new CannotProduceStatCollector(e);
                    } 
                }
                else {
                    throw new CannotProduceStatCollector("Unknown attribute: " + name);
                }
            }
            if (collector == null) throw new CannotProduceStatCollector("There is a 'do' tag without collecting action");
            if (scheduler == null) throw new CannotProduceStatCollector("There is a 'do' tag without 'at' or 'every' attribute");
            processors.add(new SingleStatProcessor(new StatProducer(collName, collector, validators), scheduler));
        }
    }

    private String extractCollName(Map<QName, String> otherAttributes) {
        for (QName name: otherAttributes.keySet()) {
            if (name.getLocalPart().equalsIgnoreCase("name")) {
                return otherAttributes.remove(name);
            }
        }
        return null;
    }

    private void parseUsages(List<Monitor.Use> useList) throws CannotProduceStatCollector {
        for (Monitor.Use use: useList) {
            Class c = null;
            try {
                if (use.getCollector() != null) {
                    c = ClassEx.getClass(StatCollector.class, use.getCollector());
                    logger.info("Collector class: " + c);
                }
                else if (use.getValidator() != null) {
                    c = ClassEx.getClass(StatValidator.class, use.getValidator());
                    logger.info("Validator class: " + c);
                }
                if (c != null) {
                    String attrSelector = ((AttributePattern) c.getAnnotation(AttributePattern.class)).value();
                    logger.info("Associated with: " + attrSelector);
                    classMap.put(attrSelector, c);
                }
            }
            catch ( Exception e) {
                throw new CannotProduceStatCollector(e);
            }
        }        
    }
}
