package com.actimind.common;

import java.util.LinkedList;

public class ExecutionResult
{

    private LinkedList<String> out;
    private LinkedList<String> err;
    private Exception exception;

    public ExecutionResult(LinkedList<String> out, LinkedList<String> err)
    {
        this.out = out;
        this.err = err;
    }

    public ExecutionResult(Exception exception)
    {
        this.exception = exception;
    }

    public LinkedList<String> getOut()
    {
        return out;
    }

    public LinkedList<String> getErr()
    {
        return err;
    }
}