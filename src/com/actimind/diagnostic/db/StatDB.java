package com.actimind.diagnostic.db;

import com.actimind.db.Connector;
import com.actimind.db.Sql;
import com.actimind.db.SqlProxy;
import org.apache.log4j.BasicConfigurator;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public interface StatDB {

    @Sql("insert into stats :insert")
    public void storeLastStat(Stat stat);

    @Sql("select s.* from stats s inner join latest_stats l on s.id = l.stats_id where l.name = :1")
    public Stat obtainLatestStat(String name);

    @Sql("select s.* from stats s inner join latest_stats l on s.id = l.stats_id")
    public Stat[] getLatestStats();

    @Sql("select * from stats where name=:1 and valueChanged = 1 order by `when` desc limit 200")
    public Stat[] getStatsFor(String name);

    @Sql("select * from stats where name=:1 order by `when` desc limit 10000")
    public Stat[] getAllStatsFor(String name);

    @Sql("select * from stats where `when` <= :2 and name=:1 order by `when` desc limit 1")
    public Stat getStatsForTime(String name, long ts);

    @Sql("delete from latest_stats where name=:1")
    public void deleteLatestStat(String name);

    @Sql("delete from stats")
    public void clearAllStats();

    @Sql("delete from latest_stats")
    public void clearAllLatestStats();

    @Sql("select count(*) from stats")
    public Integer getCountOfStats();

    @Sql("select name from stats")
    public String[] getNames();

    @Sql("select count(*) from stats where name=:name")
    public Integer getCountOfStats(Stat st);

    @Sql("select `force` from config limit 1")
    public Boolean isForceEnabled();

    @Sql("update config set `force`=:1")
    public void updateForce(Integer value);

    @Sql("update stats set valueChanged = 1 where id in (select stats_id from latest_stats where name=:name )")
    void setLastStatChanged(Stat stat);


    class R {
        public static void main(String args[]) throws SQLException, ClassNotFoundException {
            StatMapper.init();
            BasicConfigurator.configure();
            Class.forName("org.sqlite.JDBC");
            Connector cnt = new Connector("jdbc:sqlite:stats.sqlite");
            StatDB db = SqlProxy.produceSqlFacade(StatDB.class, cnt);
            Stat st = new Stat();
            st.setName("name5");
            st.setStoredValueAsString("1");
            db.storeLastStat(st);
            db.setLastStatChanged(st);
            ///System.out.println(db.getCountOfStats(st));


            //st = db.getStatsForTime("name5", new Timestamp(new Date().getTime()).getTime());
            
            System.out.println(st);
        }
    }

}
