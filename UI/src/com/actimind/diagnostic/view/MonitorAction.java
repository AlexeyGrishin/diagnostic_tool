package com.actimind.diagnostic.view;

import com.actimind.common.SyncDateFormat;
import com.actimind.db.Connector;
import com.actimind.db.SqlProxy;
import com.actimind.diagnostic.db.Stat;
import com.actimind.diagnostic.db.StatDB;
import com.actimind.diagnostic.db.StatMapper;
import com.actimind.diagnostic.fw.Action;
import com.actimind.diagnostic.fw.Renderer;
import com.actimind.diagnostic.fw.View;
import com.actimind.diagnostic.model.Timeline;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

public class MonitorAction implements Action {

    private Connector connector;

    private StatDB db;

    private String dbUrl;
    private String connectorClass;
    private Renderer renderer;

    public MonitorAction(Renderer r) {
        this.renderer = r;
    }

    public MonitorAction(Map<String, String> params) {
        setDbUrl(params.get("db-url"));
        setConnectorClass(params.get("connector-class"));
    }


    public MonitorAction(String dbUrl, String connectorClass) {
        this.dbUrl = dbUrl;
        this.connectorClass = connectorClass;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setConnectorClass(String connectorClass) {
        this.connectorClass = connectorClass;
    }


    private void init() throws Exception {
        StatMapper.init();
        Class.forName(connectorClass);
        db = SqlProxy.produceSqlFacade(StatDB.class, new Connector(dbUrl));
    }


    private List<StatView> list = new ArrayList<StatView>();
    private View view = null;

    private List<StatView> produceListOfViews(Stat[] stats) {
        for (Stat stat: stats) {
            if (stat != null) {
                list.add(new StatView(stat));
            }
        }
        Collections.sort(list);
        return list;
    }

    public List<StatView> getList() {
        return list;
    }

    public View getView() {
        return view;
    }


    public MonitorAction produceLatestStatsList() throws Exception {
        init();
        view = new StdView("Monitor", produceListOfViews(db.getLatestStats()), isUpdating());
        return this;
    }

    public MonitorAction produceListForPrepareTimeline() throws Exception {
        init();
        view = new PrepareTimelineView("Prepare Timeline", produceListOfViews(db.getLatestStats()), isUpdating());
        return this;
    }

    public MonitorAction produceStatsListForName(String name) throws Exception {
        init();
        List<StatView> list = produceListOfViews(db.getStatsFor(name));
        StatView latestOne = new StatView(db.obtainLatestStat(name));
        if (list.size() == 0 || list.get(0).compareTo(latestOne) != 0)
            list.add(0, latestOne);   //latest one
        StdView view = StdView.listWithoutRemove("Values for " + name, list);
        view.setMarkFirst(true);
        this.view = view;
        return this;
    }


    public MonitorAction produceFullStatsListForName(String name) throws Exception {
        init();
        StdView view = StdView.listWithoutRemove("Values for " + name, produceListOfViews(db.getAllStatsFor(name)));
        view.setMarkFirst(true);
        this.view = view;
        return this;
    }


    public MonitorAction produceStatsListForTime(String time) throws Exception {
        init();
        Date date = parseTime(time);
        Stat[] latest = db.getLatestStats();
        for (int i = 0; i < latest.length; i++) {
            latest[i] = db.getStatsForTime(latest[i].getName(), date.getTime());
        }
        view = new StdView("Values for " + DateView.formatFullDate(date), produceListOfViews(latest));
        return this;
    }

    final static DateFormat URL_DATE_FORMAT = new SyncDateFormat("yyyy-M-dd-HH-mm-ss");
    private Date parseTime(String time) throws ParseException {
        return URL_DATE_FORMAT.parse(time);
    }

    public MonitorAction clearAllStats() throws Exception {
        init();
        db.clearAllStats();
        db.clearAllLatestStats();
        enableForce(true);
        return this;
    }

    public MonitorAction clearStat(String name) throws Exception {
        init();
        db.deleteLatestStat(name);
        return this;
    }

    public MonitorAction enableForce(Boolean value) throws Exception {
        init();
        db.updateForce(value ? 1 : 0);
        return this;
    }

    public boolean isUpdating() {
        return db != null && db.isForceEnabled();
    }

    public Renderer then() {
        return renderer;
    }

    public MonitorAction produceTimeline(String[] names) throws Exception {
        init();
        Timeline timeline = new Timeline(names, db);
        view = new TimelineView(timeline);
        return this;
    }

}
