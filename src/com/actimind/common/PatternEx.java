package com.actimind.common;

import java.util.regex.Pattern;

public class PatternEx {

    private final static String TO_ESCAPE = "[]()$^./|*?+";

    public static String escape(String str) {
        String r = str;
        for (char c: TO_ESCAPE.toCharArray()) {
            r = r.replaceAll("\\" + c, "\\\\" + c);
        }
        return r;
    }

    public static Pattern compile(String s) {
        return Pattern.compile(escape(s));
    }

    public static Pattern compile(String s, int flags) {
        return Pattern.compile(escape(s), flags);
    }
}
