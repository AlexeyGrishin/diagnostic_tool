package com.actimind.common;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Synchronized wrapper for DateFormat class.
 */
public class SyncDateFormat extends DateFormat {

    private final DateFormat format;

    public SyncDateFormat(DateFormat format) {
        this.format = format;
    }

    public SyncDateFormat(String format) {
        this.format = new SimpleDateFormat(format);
    }

    @Override
    public synchronized StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return format.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public synchronized Date parse(String source, ParsePosition pos) {
        return format.parse(source, pos);
    }

    @Override
    public synchronized Date parse(String source) throws ParseException {
        return format.parse(source);    
    }

    @Override
    public synchronized Object parseObject(String source, ParsePosition pos) {
        return format.parseObject(source, pos);    
    }

    @Override
    public synchronized void setCalendar(Calendar newCalendar) {
        format.setCalendar(newCalendar);    
    }

    @Override
    public synchronized Calendar getCalendar() {
        return format.getCalendar();    
    }

    @Override
    public synchronized void setNumberFormat(NumberFormat newNumberFormat) {
        format.setNumberFormat(newNumberFormat);    
    }

    @Override
    public synchronized NumberFormat getNumberFormat() {
        return format.getNumberFormat();    
    }

    @Override
    public synchronized void setTimeZone(TimeZone zone) {
        format.setTimeZone(zone);    
    }

    @Override
    public synchronized TimeZone getTimeZone() {
        return format.getTimeZone();    
    }

    @Override
    public synchronized void setLenient(boolean lenient) {
        format.setLenient(lenient);    
    }

    @Override
    public synchronized boolean isLenient() {
        return format.isLenient();    
    }

    @Override
    public synchronized AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return format.formatToCharacterIterator(obj);    
    }

    @Override
    public synchronized  Object parseObject(String source) throws ParseException {
        return format.parseObject(source);    
    }



}
