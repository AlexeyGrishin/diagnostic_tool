package com.actimind.diagnostic.view;

import com.actimind.common.DateFormatEx;
import com.actimind.common.SyncDateFormat;
import com.actimind.diagnostic.db.Stat;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class StatView implements Comparable<StatView> {

    private Stat stat;
    private static final int MILLIS_IN_DAY = (1000*60*60*24);

    public StatView(Stat stat) {
        this.stat = stat;
    }
    public String getName() {
        return stat.getName();
    }


    public String getDate() {
        return DateView.formatDate(stat.getWhen());
    }

    public String getLinkToDate() {
        return String.format("<a href=\"%s\" target=\"_blank\">%s</a>", ViewHelper.linkToTime(stat), getDate());
    }

    public String getDateInterval() {
        return DateFormatEx.formatDate(stat.getWhen());
    }

    public String getLinkToName() {
        return String.format("<a href=\"%s\" target=\"_blank\">%s</a>", ViewHelper.linkToName(stat), getName());
    }

    public String getFullDate() {
        return MonitorAction.URL_DATE_FORMAT.format(stat.getWhen());
    }

    public String getLinkToDelete() {
        return String.format("<a class=\"to-delete\" href=\"%s\">%s</a>", ViewHelper.linkToDelete(stat), "x");
    }


    public boolean isNormal() {
        return stat.isNormalState();
    }

    public String getValue() {
        return stat.getStoredValueAsString();
    }

    public String getDescription() {
        return stat.getStateDescription();
    }

    public String toJson() {
        StringBuilder bld = new StringBuilder();
        bld.append("{");
        bld.append(" name: \"").append(getName()).append("\", ");
        bld.append(" value: \"").append(getValue()).append("\", ");
        bld.append(" date: \"").append(getDate()).append("\", ");
        bld.append(" normal: \"").append(isNormal()).append("\", ");
        bld.append(" description: \"").append(getDescription()).append("\"");
        bld.append("}");
        return bld.toString();
    }


    public int compareTo(StatView o) {
        int r = getName().compareTo(o.getName());
        if (r == 0) {
            r = -stat.getWhen().compareTo(o.stat.getWhen());
        }
        return r;

    }
}
