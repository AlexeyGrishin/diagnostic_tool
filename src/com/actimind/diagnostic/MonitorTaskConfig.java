package com.actimind.diagnostic;

import java.util.Collection;

public interface MonitorTaskConfig {

    /**
     *
     * @return true if all stats shall be collected right now independently on their schedule.
     *
     * Force flag is set externally and is reset by {@link #resetForce()}
     */
    boolean isForce();

    public Collection<SingleStatProcessor> getProcessors();

    /**
     * Monitor resets force flag after all stats were collected. External clients can check force flag
     * in order to know when stats recollection is finished.
     */
    void resetForce();
}
