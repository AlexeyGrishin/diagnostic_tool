package com.actimind.diagnostic.date;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PeriodicalScheduler implements Scheduler {

    private long milliseconds;

    public PeriodicalScheduler(int time, TimeUnit unit) {
        this.milliseconds = unit.toMillis(time);
    }

    public Date getNextDate() {
        return new Date(System.currentTimeMillis() + milliseconds);
    }
}
