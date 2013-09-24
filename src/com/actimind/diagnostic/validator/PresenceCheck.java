package com.actimind.diagnostic.validator;

public class PresenceCheck {

    public static boolean isPresent(Object o) {
        if (o == null) return false;

        if (o instanceof Number) {
            return isPresent((Number)o);
        }
        else if (o instanceof String) {
            return isPresent((String)o);
        }
        else if (o instanceof Boolean) {
            return (Boolean)o;
        }

        return true;
    }

    private static boolean isPresent(Number o) {
        return o.longValue() > 0;
    }

    private static boolean isPresent(String s) {
        return !s.trim().isEmpty();
    }

}
