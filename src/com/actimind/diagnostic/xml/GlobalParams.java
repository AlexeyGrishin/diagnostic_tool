package com.actimind.diagnostic.xml;

import com.actimind.diagnostic.stats.ParamsAware;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class GlobalParams {

    private Map<String, String> global = new HashMap<String, String>();

    public GlobalParams(Map<String, String> global) {
        this.global.putAll(global);
    }

    public GlobalParams() {
        global = new HashMap<String, String>();
    }

    public void put(String p, String v) {
        global.put(p, v);
    }

    public GlobalParams withLocalParams(Collection<ParamsType> params) {
        GlobalParams p = copy();
        for (ParamsType t: params) {
            p.put(t.name, t.value);
        }
        return p;
    }

    public <T> T apply(T instance) throws Exception {
        if (instance instanceof ParamsAware) {
            ((ParamsAware)instance).setParams(global);
        }
        return instance;
    }
    
    public GlobalParams copy() {
        return new GlobalParams(global);
    }

}
