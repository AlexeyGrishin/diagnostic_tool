package com.actimind.diagnostic.stats;

/**
 * Performs single measurement and returns its result.
 * Result object shall support toString operation.
 *
 * Implementations of this interface shall have public constructor
 * with string parameter - it will be set with parameter from monitor.xml (see example).
 * 
 */
public interface StatCollector<T> {

    /**
     * Performs single measurment and returns its result.
     * @return measuremnt result
     * @throws Exception if something went wrong
     */
    T getStat() throws Exception;

}
