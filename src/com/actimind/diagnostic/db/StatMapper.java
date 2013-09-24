package com.actimind.diagnostic.db;

import com.actimind.db.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StatMapper implements Mapper<Stat> {

    public Stat produceFrom(ResultSet result) throws SQLException {
        Stat stat = new Stat();
        stat.setName(result.getString("name"));
        stat.setStoredValueAsString(result.getString("value"));
        stat.setNormalState(result.getInt("normal") > 0);
        stat.setValueChanged(result.getInt("valueChanged") > 0);
        stat.setWhen(new Date(result.getTimestamp("when").getTime()));
        stat.setStateDescription(result.getString("stateDescription"));
        return stat;
    }

    public Map<String, Object> toMap(Stat object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", object.getName());
        map.put("value", object.getStoredValueAsString());
        map.put("valueChanged", object.isValueChanged());
        map.put("normal", object.isNormalState());
        map.put("when", object.getWhen());
        map.put("stateDescription", object.getStateDescription());
        return map;
    }

    static {
        init();
    }

    public static void init() {
        Mapper.Registry.addMapper(Stat.class, new StatMapper());
    }
}
