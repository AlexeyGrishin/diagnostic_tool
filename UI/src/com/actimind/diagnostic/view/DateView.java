package com.actimind.diagnostic.view;

import com.actimind.common.SyncDateFormat;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateView {

    static final DateFormat NOT_TODAY = new SyncDateFormat("(E) dd MMM, yyyy HH:mm:ss");
    static final DateFormat TODAY = new SyncDateFormat("HH:mm:ss");
    

    public static String formatDate(Date date) {
        return isToday(date) ? TODAY.format(date) : NOT_TODAY.format(date);
    }

    public static String formatFullDate(Date date) {
        return NOT_TODAY.format(date);
    }


    private static boolean isToday(Date date) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return date.after(c.getTime());
    }
}
