package com.actimind.diagnostic.view;

import com.actimind.diagnostic.db.Stat;

import java.net.URLEncoder;

public class ViewHelper {

    public static String linkToTime(Stat st) {
        return "info-for-time.jsp?date=" + MonitorAction.URL_DATE_FORMAT.format(st.getWhen());
    }

    public static String linkToName(Stat st) {
        return "info-for.jsp?name=" + URLEncoder.encode(st.getName());
    }

    public static String linkToDelete(Stat st) {
        return "clear.jsp?name=" + URLEncoder.encode(st.getName());
    }

    public static String linkToDeleteAll() {
        return "clear.jsp";
    }

    public static String linkToForce(boolean b) {
        return "force.jsp?force=" + b;
    }

    public static String linkToFull(String parameter) {
        return "full-info-for.jsp?name=" + URLEncoder.encode(parameter);
    }

    public static String timeline() {
        return "timeline.jsp";
    }

    public static String linkToTimelineCsv(String[] parameterValues) {
        return linkToTimelineCsv(parameterValues, ",");
    }

    public static String linkToTimelineCsv(String[] parameterValues, String sep) {
        StringBuilder bld = new StringBuilder();
        boolean first = true;
        for (String s: parameterValues) {
            if (!first) bld.append("&");
            bld.append("names=").append(s);
            first = false;
        }
        return "timeline.csv.jsp?" + bld.toString() + "&s=" + sep;
    }
}
