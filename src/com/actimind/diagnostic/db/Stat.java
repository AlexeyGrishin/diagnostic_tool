package com.actimind.diagnostic.db;

import java.util.Date;

/**
 * Represents single statistic value stored in DB
 */
public class Stat {

    private Date when = new Date();
    private String name;
    private boolean normalState = true;
    private boolean valueChanged = false;
    private String storedValueAsString;
    private String stateDescription = "";

    public Stat(String name) {
        this.name = name;
    }

    public Stat(String name, Date when) {
        this.name = name;
        this.when = when;
        normalState = true;
    }

    public Stat() {
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNormalState() {
        return normalState;
    }

    public void setNormalState(boolean normalState) {
        this.normalState = normalState;
    }

    public String getStoredValueAsString() {
        return storedValueAsString;
    }

    public String getValue() {
        return storedValueAsString;
    }

    public void setStoredValueAsString(String storedValueAsString) {
        this.storedValueAsString = storedValueAsString == null ? "" : storedValueAsString;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }


    public boolean wasChanged(Stat previous) {
        if (previous == null)
            return true;
        return  previous.isNormalState() != isNormalState() 
                || !previous.getStateDescription().equals(stateDescription);
    }

    public boolean isValueChanged() {
        return valueChanged;
    }

    public void setValueChanged(boolean valueChanged) {
        this.valueChanged = valueChanged;
    }

    public String toString() {
        return name + "=" + storedValueAsString + " [" + (normalState ? "Normal" : stateDescription) + "]"; 
    }

    public boolean valueWasChanged(Stat previousStat) {
        if (previousStat == null)
            return true;
        return !previousStat.getValue().equals(getValue()); 
    }
}
