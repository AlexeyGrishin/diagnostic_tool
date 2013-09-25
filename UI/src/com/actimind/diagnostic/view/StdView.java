package com.actimind.diagnostic.view;

import com.actimind.diagnostic.fw.View;

import java.util.List;

public class StdView implements View {
    
    private List<StatView> list;
    private String title;
    private boolean updating = false;
    private boolean remoteButton = true;

    public StdView(String title, List<StatView> list) {
        this.title = title;
        this.list = list;
    }

    public StdView(String title, List<StatView> list, boolean updating) {
        this.list = list;
        this.title = title;
        this.updating = updating;
    }


    public StdView(String title, List<StatView> list, boolean updating, boolean remoteButton) {
        this.list = list;
        this.title = title;
        this.updating = updating;
        this.remoteButton = remoteButton;
    }

    boolean markFirst = false;

    public void setMarkFirst(boolean markFirst) {
        this.markFirst = markFirst;
    }

    public String getClassForFirst() {
        return markFirst ? "first" : "";
    }

    public static StdView list( String title, List<StatView> list) {
        return new StdView(title, list);
    }

    public static StdView listWithoutRemove( String title, List<StatView> list) {
        return new StdView(title, list, false, false);
    }
    public boolean isUpdating() {
        return updating;
    }

    public List<StatView> getList() {
        return list;
    }

    public boolean isShowRemoveButton() {
        return remoteButton;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return "";
    }

    public boolean isTimelineSelected() {
        return false;
    }
}
