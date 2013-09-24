package com.actimind.common;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

/**
 * Date/Time format helper, represents date/time as an amount of minutes/hours/days/etc ago.
 * In english only (does not support i18n)
 */
public class DateFormatEx extends DateFormat {


    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        long diff = new Date().getTime() - date.getTime();
        toAppendTo.append(diff > 0 ? past(diff) : future(-diff));
        return toAppendTo;
    }

    private static DateFormat instance = new SyncDateFormat(new DateFormatEx());

    public static String formatDate(Date date) {
        return instance.format(date);
    }

    public static String past(long diff) {
        return format(diff) + " ago";
    }

    public static String future(long diff) {
        return format(diff) + " after";
    }

    public static String format(long diff) {
        long seconds = diff / 1000;
        if (seconds < 60) {
            return "A moment";
        }
        long minutes = seconds / 60;
        if (minutes < 60) {
            return m(minutes, "minute");
        }
        long hours = minutes / 60;
        long minutes_in_hour = minutes % 60;
        if (hours < 24) {
            String hr = m(hours, "hour");
            if (minutes_in_hour > 0)
                hr += " " + m(minutes_in_hour, "minute");
            return hr;
        }
        long days = hours / 24;
        long hour_in_day = days % 24;
        String st = m(days, "day");
        if (hour_in_day > 0)
            st += " " + m(hour_in_day, "hour");
        return st;
    }

    private static String m(long n, String s) {
        return n + " " + s + (n == 1 ? "" : "s");
    }


    @Override
    public Date parse(String source, ParsePosition pos) {
        return null;
    }
}
