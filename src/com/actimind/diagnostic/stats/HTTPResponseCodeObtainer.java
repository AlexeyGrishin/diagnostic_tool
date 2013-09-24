package com.actimind.diagnostic.stats;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.*;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Performs HTTP GET query for url specified as parameter.
 * Returns result code or "Timeout" string.
 */
@TextPattern("GET HTTP :param")
@AttributePattern("http-get")
public class HTTPResponseCodeObtainer implements StatCollector, ParamsAware {

    private URL url;
    private final static int TIMEOUT_MS = 5000;
    private int timeout = TIMEOUT_MS;

    public HTTPResponseCodeObtainer(String urlStr) throws MalformedURLException {
        url = new URL(urlStr);
    }

    public void setParams(Map<String, String> global) throws Exception {
        String timeoutValue = global.get("http-timeout-ms");
        if (timeoutValue != null) {
            try {
                timeout = Integer.parseInt(timeoutValue);
            }
            catch (Exception e) {
                Logger.getLogger(getClass()).error("Cannot parse http-timeout-ms value " + timeoutValue, e);
                
            }
        }
    }
    
    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public Object getStat() throws Exception {

        final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                conn.connect();
                return conn.getResponseCode();
            }
        });
        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException e) {
            future.cancel(true);
            return "Timeout";
        }
        catch (ExecutionException e) {
            if (e.getCause() instanceof SocketTimeoutException) {
                return "Timeout";
            }
            return "Not Available";
        }
    }
}
