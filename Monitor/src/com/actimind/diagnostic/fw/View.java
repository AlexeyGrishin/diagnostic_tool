package com.actimind.diagnostic.fw;

import com.actimind.diagnostic.db.Stat;

import java.util.List;

public interface View {

    public boolean isUpdating();

    public boolean isShowRemoveButton();

    public String getTitle();
    
    public String getSubtitle();

    public boolean isTimelineSelected();
}
