package com.actimind.diagnostic;

public class CannotProduceStatCollector extends Exception {

    public CannotProduceStatCollector() {
    }

    public CannotProduceStatCollector(String message) {
        super(message);
    }

    public CannotProduceStatCollector(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotProduceStatCollector(Throwable cause) {
        super(cause);
    }
}
