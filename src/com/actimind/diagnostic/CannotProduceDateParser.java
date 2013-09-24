package com.actimind.diagnostic;

public class CannotProduceDateParser extends Exception {
    public CannotProduceDateParser() {
    }

    public CannotProduceDateParser(String message) {
        super(message);
    }

    public CannotProduceDateParser(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotProduceDateParser(Throwable cause) {
        super(cause);
    }
}
