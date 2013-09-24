package com.actimind.diagnostic.stats;

import com.actimind.common.ExecutionResult;
import com.actimind.common.ProcessCommander;
import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;
import org.apache.log4j.Logger;

@TextPattern("Count of ':param' running")
@AttributePattern("process-count")
public class WmicProcessCounter implements StatCollector<Integer> {

    private final String CMD = "WMIC PROCESS WHERE(commandLine like \"%%%s%%\" and not commandLine like \"wmic%%\") GET processid";
    private Logger logger = Logger.getLogger(getClass());

    private String commandLinePattern;

    public WmicProcessCounter(String commandLinePattern) {
        this.commandLinePattern = String.format(CMD, commandLinePattern);
    }

    public Integer getStat() throws Exception {
        logger.info(commandLinePattern);
        ExecutionResult result = ProcessCommander.exec(commandLinePattern);
        if (result.getOut().size() > 1) {
            try {
                long pid = Long.parseLong(result.getOut().get(1).trim());
                if (pid > 0) return result.getOut().size() - 1;
            }
            catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
}
