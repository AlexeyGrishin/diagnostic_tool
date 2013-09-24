package com.actimind.diagnostic.stats;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;

import java.io.File;

/**
 * Calculates free disk space on specified disk.
 * Returns value in MB
 */
@TextPattern("Get free disk space for :param")
@AttributePattern("disk-space-on")
public class AvailableDiskSpaceCounter implements StatCollector {

    private File disk;
    private final static int MB = 1024*1024;

    public AvailableDiskSpaceCounter(String disk) {
        this.disk = new File(disk);
    }

    public Object getStat() throws Exception {

        return disk.getFreeSpace() / MB;
    }
}
