package com.actimind.diagnostic.date;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultDateParser implements DateParser {


    private Pattern msh = Pattern.compile("^\\s*([0-9]+)\\s*(m|s|h)s?\\s*$", Pattern.CASE_INSENSITIVE);
    private Pattern atDate = Pattern.compile("^\\s*([0-9]+)[\\.:-]([0-9]+)\\s*$", Pattern.CASE_INSENSITIVE);

    public Scheduler parse(String str) {
        Matcher m = msh.matcher(str);
        if (m.find()) {
            TimeUnit t = null;
            if (str.contains("m")) t = TimeUnit.MINUTES;
            if (str.contains("s")) t = TimeUnit.SECONDS;
            if (str.contains("h")) t = TimeUnit.HOURS;
            return new PeriodicalScheduler(Integer.valueOf(m.group(1)), t);
        }
        Matcher d = atDate.matcher(str);
        if (d.find()) {
            return new SpecificTimeScheduler(Integer.valueOf(d.group(1)), Integer.valueOf(d.group(2)));
        }
        return null;
    }
}
