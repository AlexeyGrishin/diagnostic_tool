package com.actimind.diagnostic.date;

import java.util.Calendar;
import java.util.Date;

public class SpecificTimeScheduler implements Scheduler {

    private int h;
    private int m;

    public SpecificTimeScheduler(int h, int m) {
        this.h = h;
        this.m = m;
    }

    public Date getNextDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, h);
        c.set(Calendar.MINUTE, m);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        return c.getTime();
    }
}
