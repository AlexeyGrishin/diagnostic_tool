package com.actimind.diagnostic.stats;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;
import org.apache.log4j.Logger;

import java.rmi.*;
import java.util.Map;

@TextPattern("Check :param availability over RMI")
@AttributePattern("rmi")
public class RMIAvailabilityChecker implements StatCollector<Boolean>, ParamsAware {

    private String name;
    private String registry;

    public RMIAvailabilityChecker(String name) {
        this.name = name.trim();
    }

    public void setParams(Map<String, String> global) throws Exception {
        this.registry = global.get("rmi-registry");
    }

    public Boolean getStat() throws Exception {
        try {

            for (String s: Naming.list(registry)) {
                if (s.endsWith("/" +name)) {
                    return true;
                }
            }
            return false;
        }
        catch (RemoteException e) {
            Logger.getLogger(getClass()).error("Cannot check RMI availability", e);
            return false;
        }
    }

}
