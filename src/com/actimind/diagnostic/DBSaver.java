package com.actimind.diagnostic;

import com.actimind.diagnostic.db.Stat;
import com.actimind.diagnostic.db.StatDB;
import com.actimind.diagnostic.listeners.StatProcessorListener;

class DBSaver implements StatProcessorListener {

    private StatDB db;

    public DBSaver(StatDB db) {
        this.db = db;
    }

    public void onNewStatCollected(Stat stat) {
        db.storeLastStat(stat);
    }

    public void onStatStateChanged(Stat stat) {
        db.setLastStatChanged(stat);
    }

    public void onFirstStat(Stat stat) {
        db.setLastStatChanged(stat);
    }

    public void onStatValueChanged(Stat stat) {
        db.setLastStatChanged(stat);
    }
}
