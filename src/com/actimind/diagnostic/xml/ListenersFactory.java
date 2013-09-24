package com.actimind.diagnostic.xml;

import com.actimind.common.ClassEx;
import com.actimind.diagnostic.listeners.StatProcessorListener;
import com.actimind.diagnostic.stats.ParamsAware;

import java.util.List;

public class ListenersFactory {

    public void fillListeners(Monitor m, List<StatProcessorListener> list, GlobalParams globalParams) throws Exception, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (m.getListeners() == null) return;
        for (Monitor.Listeners.Listen l: m.getListeners().getListen()) {
            StatProcessorListener listener = ClassEx.createInstance(StatProcessorListener.class, l.getWith());
            if (listener instanceof ParamsAware) {
                GlobalParams params = globalParams.withLocalParams(l.getParam());
                params.apply(listener);
            }
            list.add(listener);
        }
    }

}
