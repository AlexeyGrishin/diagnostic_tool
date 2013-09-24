
package com.actimind.diagnostic.xml;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.actimind.diagnostic.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.actimind.diagnostic.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Monitor }
     * 
     */
    public Monitor createMonitor() {
        return new Monitor();
    }

    /**
     * Create an instance of {@link Monitor.Listeners }
     * 
     */
    public Monitor.Listeners createMonitorListeners() {
        return new Monitor.Listeners();
    }

    /**
     * Create an instance of {@link Monitor.Stats }
     * 
     */
    public Monitor.Stats createMonitorStats() {
        return new Monitor.Stats();
    }

    /**
     * Create an instance of {@link Monitor.Stats.Stat }
     * 
     */
    public Monitor.Stats.Stat createMonitorStatsStat() {
        return new Monitor.Stats.Stat();
    }

    /**
     * Create an instance of {@link Monitor.Use }
     * 
     */
    public Monitor.Use createMonitorUse() {
        return new Monitor.Use();
    }

    /**
     * Create an instance of {@link Monitor.Global }
     * 
     */
    public Monitor.Global createMonitorGlobal() {
        return new Monitor.Global();
    }

    /**
     * Create an instance of {@link Monitor.DateParsers }
     * 
     */
    public Monitor.DateParsers createMonitorDateParsers() {
        return new Monitor.DateParsers();
    }

    /**
     * Create an instance of {@link ParamsType }
     * 
     */
    public ParamsType createParamsType() {
        return new ParamsType();
    }

    /**
     * Create an instance of {@link Monitor.Listeners.Listen }
     * 
     */
    public Monitor.Listeners.Listen createMonitorListenersListen() {
        return new Monitor.Listeners.Listen();
    }

    /**
     * Create an instance of {@link Monitor.Stats.Do }
     * 
     */
    public Monitor.Stats.Do createMonitorStatsDo() {
        return new Monitor.Stats.Do();
    }

    /**
     * Create an instance of {@link Monitor.Stats.Stat.Collect }
     * 
     */
    public Monitor.Stats.Stat.Collect createMonitorStatsStatCollect() {
        return new Monitor.Stats.Stat.Collect();
    }

    /**
     * Create an instance of {@link Monitor.Stats.Stat.Validate }
     * 
     */
    public Monitor.Stats.Stat.Validate createMonitorStatsStatValidate() {
        return new Monitor.Stats.Stat.Validate();
    }

}
