package com.actimind.diagnostic.model;

import com.actimind.diagnostic.db.Stat;
import com.actimind.diagnostic.db.StatDB;

import java.util.*;


public class Timeline extends ArrayList<Timeline.TimelineItem> {
    private String[] names;

    public static class TimelineItem {
        private Date date;
        private List<Stat> stats;

        TimelineItem(Date date, List<Stat> stats) {
            this.date = date;
            this.stats = stats;
        }

        public Date getDate() {
            return date;
        }

        public List<Stat> getStats() {
            return stats;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (o instanceof TimelineItem) {
                TimelineItem o2 = (TimelineItem) o;
                if (o2.date.equals(date)) return true;
                for (int i = 0; i < stats.size(); i++) {
                    switch(compareNls(stats.get(i), o2.stats.get(i))) {
                        case BOTH_NULL: continue;
                        case ONE_NULL: return false;
                    }
                    if (stats.get(i) == null && o2.stats.get(i) != null) return false;
                    if (stats.get(i) != null && o2.stats.get(i) == null) return false;
                    if (!stats.get(i).getStoredValueAsString().equals(o2.stats.get(i).getStoredValueAsString()))
                        return false;
                }
                return true;
                        
            }
            return false;
        }

    }


    enum NullComparison {
        BOTH_NULL,
        ONE_NULL,
        NO_NULLS
    }

    private static NullComparison compareNls(Object o, Object o2) {
        if (o != null && o2 != null) return NullComparison.NO_NULLS;
        if (o == o2) return NullComparison.BOTH_NULL;
        return NullComparison.ONE_NULL;
    }

    private static final Comparator<? super Stat> BY_DATE = new Comparator<Stat>() {
        public int compare(Stat o1, Stat o2) {
            return o1.getWhen().compareTo(o2.getWhen());
        }
    };


    public Timeline(String[] names, StatDB db) {
        this.names = names;

        List<List<Stat>> stats = new ArrayList<List<Stat>>();
        for (String name: names) {
            stats.add(getStats(name, db));
        }
        merge(stats);
    }

    private void merge(List<List<Stat>> stats) {
        Iterators<Stat> iterators = new Iterators<Stat>(stats);
        while (iterators.hasValues()) {
            List<Stat> nextValues = iterators.getNextValues();
            Date minDate = getMinDate(nextValues);
            for (int i = 0; i < iterators.count(); i++) {
                if (sameDate(nextValues.get(i), minDate)) {
                    iterators.goNext(i);
                }
            }
            addIfNotNullAndLatestNotSame(new TimelineItem(minDate, iterators.getCurrentValues()));
        }
    }

    private void addIfNotNullAndLatestNotSame(TimelineItem item) {
        if (size() != 0) {
            if (get(size() - 1).equals(item)) return;
        }
        for (Stat st: item.getStats()) {
            if (st == null) return;
        }
        add(item);
    }

    public String[] getNames() {
        return names;
    }

    private boolean sameDate(Stat val, Date minDate) {
        return val != null && val.getWhen().equals(minDate);
    }

    private Date getMinDate(List<Stat> vals) {
        List<Date> dates = new ArrayList<Date>();
        for (Stat st: vals) if (st != null) dates.add(st.getWhen());
        return Collections.min(dates);
    }


    private List<Stat> getStats(String name, StatDB db) {
        List<Stat> list = new ArrayList<Stat>();
        //list.add(db.obtainLatestStat(name));
        list.addAll(Arrays.asList(db.getStatsFor(name)));
        Collections.sort(list, BY_DATE);
        return list;
    }


    class Iterators<T> {

        private List<List<T>> lists;

        private List<Integer> currentIndexes = new ArrayList<Integer>();

        Iterators(List<List<T>> lists) {
            this.lists = lists;
            for (List<T> list : lists) {
                currentIndexes.add(-1);
            }
        }

        private T getValue(int list) {
            return getValue(list, getIndex(list));
        }

        private T getValue(int list, int index) {
            List<T> listVal = getList(list);
            if (listVal.size() > index && index >= 0)
                return listVal.get(index);
            return null;
        }


        private boolean hasMoreValue(int list) {
            return getList(list).size() - 1 > getIndex(list);
        }

        private void goNext(int list) {
            if (hasMoreValue(list))
                currentIndexes.set(list, getIndex(list)+1);
        }

        public T getNextValue(int list) {
            return getValue(list, getIndex(list) + 1);
        }

        private void goPrev(int list) {
            if (getIndex(list) >= 0) {
                currentIndexes.set(list, getIndex(list) - 1);
            }
        }

        private Integer getIndex(int list) {
            return currentIndexes.get(list);
        }

        private List<T> getList(int list) {
            return lists.get(list);
        }

        public List<T> getCurrentValues() {
            List<T> values = new ArrayList<T>();
            for (int i = 0; i < count(); i++) {
                values.add(getValue(i));
            }
            return values;
        }

        public List<T> getNextValues() {
            List<T> values = new ArrayList<T>();
            for (int i = 0; i < count(); i++) {
                values.add(getNextValue(i));
            }
            return values;
        }

        private int count() {
            return lists.size();
        }

        public boolean hasValues() {
            for (int i = 0; i < count(); i++) {
                if (hasMoreValue(i)) return true;
            }
            return false;
        }
    }

}
