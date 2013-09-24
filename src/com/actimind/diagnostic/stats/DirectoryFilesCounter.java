package com.actimind.diagnostic.stats;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;

import java.io.File;

@TextPattern("Count of files in :param")
@AttributePattern("count-of-files-in")
public class DirectoryFilesCounter implements StatCollector<Integer> {

    private File dir;

    public DirectoryFilesCounter(String dir) {
        this.dir = new File(dir);
    }

    public Integer getStat() throws Exception {
        try {
            return dir.list().length;
        }
        catch (Exception e) {
            return 0;
        }
    }
}
