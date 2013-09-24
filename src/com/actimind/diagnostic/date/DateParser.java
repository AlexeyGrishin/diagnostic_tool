package com.actimind.diagnostic.date;

public interface DateParser {

    /**
     * Interprets the specified string as a time period
     * and creates corresponding scheduler object.
     * @return scheduler object or null if string cannot be parsed
     */
    Scheduler parse(String str);

}
