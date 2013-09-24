package com.actimind.diagnostic.view;

import java.util.List;

public class PrepareTimelineView extends StdView {
    public PrepareTimelineView(String title, List<StatView> list, boolean updating) {
        super(title, list, updating);
    }

    @Override
    public boolean isTimelineSelected() {
        return true;
    }
}
