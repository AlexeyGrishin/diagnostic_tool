package com.actimind.diagnostic.view;

import com.actimind.common.DateFormatEx;
import com.actimind.diagnostic.db.Stat;
import com.actimind.diagnostic.fw.View;
import com.actimind.diagnostic.model.Timeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimelineView implements View {

    private Timeline timeline;
    private List<TimelineItemView> timelineView = new ArrayList<TimelineItemView>();

    public TimelineView(Timeline timeline) {
        this.timeline = timeline;
        for (Timeline.TimelineItem item: timeline) {
            timelineView.add(0, new TimelineItemView(item));
        }
    }

    public String[] getStats() {
        return timeline.getNames();
    }

    public List<TimelineItemView> getList() {
        return timelineView;
    }

    public boolean isUpdating() {
        return false;
    }

    public boolean isShowRemoveButton() {
        return false;
    }

    public String getTitle() {
        return "Timeline";
    }

    public String getSubtitle() {
        return Arrays.toString(timeline.getNames());
    }

    public boolean isTimelineSelected() {
        return true;
    }

    public class TimelineItemView {

        private String date;
        private String interval;

        private List<StatView> stats;

        TimelineItemView(Timeline.TimelineItem item) {
            date = DateView.formatDate(item.getDate());
            interval = DateFormatEx.formatDate(item.getDate());
            stats = new ArrayList<StatView>();
            for (Stat st: item.getStats()) {
                stats.add(st == null ? null : new StatView(st));
            }
        }

        public String getDate() {
            return date;
        }

        public String getInterval() {
            return interval;
        }

        public List<StatView> getStats() {
            return stats;
        }
    }
}
