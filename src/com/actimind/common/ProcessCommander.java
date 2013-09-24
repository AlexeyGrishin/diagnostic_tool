package com.actimind.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessCommander
{
    /**
     * Starts sync process and returns output/error streams after it is terminated
     *
     * @param cmd command line to be started
     * @return execution result - out/err streams
     * @throws InterruptedException
     */
    public static ExecutionResult exec(String cmd) throws InterruptedException
    {
        Runtime runtime = Runtime.getRuntime();
        try
        {
            java.lang.Process proc = runtime.exec(cmd);

            InputStream in = proc.getInputStream();
            InputStream err = proc.getErrorStream();
            OutputStream out = proc.getOutputStream();

            ProcessResultLinkedList outResultList = new ProcessResultLinkedList();
            ProcessResultLinkedList errResultList = new ProcessResultLinkedList();
            /* hack to fix hanging. java can't work with process correctly.
             * probably will be changed to use native methods. */
            boolean inputClosed = false;
            while (true)
            {
                try
                {
                    outResultList.addFromStream(in);
                    errResultList.addFromStream(err);
                    int exitVal = proc.exitValue();
                    return new ExecutionResult(outResultList, errResultList);
                }
                catch (IllegalThreadStateException e)
                {
                    // Process is not finished yet;
                    // First try to just close the input stream (maybe it awaits the input)
                    if (!inputClosed)
                    {
                        out.close();
                        inputClosed = true;
                    }
                    else
                    {
                        Thread.sleep(500);
                    }
                }
            }
        }
        catch (IOException e)
        {
            return new ExecutionResult(e);
        }
    }
}
