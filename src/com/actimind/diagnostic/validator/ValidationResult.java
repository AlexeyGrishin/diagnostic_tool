package com.actimind.diagnostic.validator;

/**
 * Contains value validation result (is it ok or not)
 * and corresponding message.
 *
 * Message shall be specified for error result, but may be omitted for normal result
 */
public class ValidationResult {

    public final boolean ok;
    public final String message;

    public ValidationResult(boolean ok, String message) {
        this.ok = ok;
        this.message = emptyIfNull(message);
    }

    private String emptyIfNull(String message) {
        return message == null ? "" : message;
    }

    public static ValidationResult ok() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult ok(String msg) {
        return new ValidationResult(true, msg);
    }

    public static ValidationResult error(String msg) {
        return new ValidationResult(false, msg);
    }

    public static ValidationResult okIfNull(String s) {
        return s == null ? ok() : error(s);
    }
}
