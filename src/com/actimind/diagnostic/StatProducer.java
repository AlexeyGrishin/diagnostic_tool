package com.actimind.diagnostic;

import com.actimind.diagnostic.db.Stat;
import com.actimind.diagnostic.stats.StatCollector;
import com.actimind.diagnostic.validator.StatValidator;
import com.actimind.diagnostic.validator.ValidationResult;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatProducer<T> {

    private String name;
    private StatCollector<T> collector;
    private List<StatValidator<T>> validators = new ArrayList<StatValidator<T>>();
    private Logger logger = Logger.getLogger(getClass());

    public StatProducer(String name, StatCollector<T> collector, List<StatValidator<T>> validators) {
        this.name = name;
        this.collector = collector;
        this.validators = validators;
    }


    public Stat produceStat() {
        Stat stat = new Stat(name, new Date());
        try {
            T value = collector.getStat();
            stat.setStoredValueAsString(value.toString());
            for (StatValidator validator: validators) {
                ValidationResult res = validator.validate(value);
                stat.setNormalState(res.ok);
                stat.setStateDescription(res.message);
                if (!res.ok) break;
            }
        }
        catch (Exception e) {
            logger.error("Error on stat processing", e);
            stat.setNormalState(false);
            stat.setStateDescription("Cannot produce stat: " + e);
        }
        return stat;
    }

    public String getName() {
        return name;
    }

}
