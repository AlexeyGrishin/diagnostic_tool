package com.actimind.diagnostic.stats;

import java.util.Map;

/**
 * Marks objects which require parameters to be set.
 *
 * Monitor.xml allows to specify global parameters and parameters for specific entity (collector or listener).
 * "local" parameters have priority over global parameters, so it there is global parameter with name "a'
 * and local one with the same name, but another value, the value of local parameter will be placed
 * into the params map
 *
 * @see com.actimind.diagnostic.listeners.StatProcessorListener
 * @see com.actimind.diagnostic.stats.StatCollector
 */
public interface ParamsAware {

    public void setParams(Map<String, String> global) throws Exception;
}
