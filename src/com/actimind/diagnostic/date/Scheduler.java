package com.actimind.diagnostic.date;

import java.util.Date;

/**
 * Represents a rule for next action time calculation.
 * Calculates date/time starting from current moment.
 */
public interface Scheduler {

    /**
     *
     * @return the next date from the current moment
     */
    public Date getNextDate();

}
