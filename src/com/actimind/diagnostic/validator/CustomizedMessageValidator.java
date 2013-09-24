package com.actimind.diagnostic.validator;

public class CustomizedMessageValidator implements StatValidator {

    private StatValidator wrapped;
    private String normalMessage;
    private String warningMessage;

    public CustomizedMessageValidator(StatValidator wrapped, String normalMessage, String warningMessage) {
        this.wrapped = wrapped;
        this.normalMessage = normalMessage;
        this.warningMessage = warningMessage;
    }

    public ValidationResult validate(Object value) {
        ValidationResult result = wrapped.validate(value);
        if (result.ok && normalMessage != null) {
            result = ValidationResult.ok(normalMessage);
        }
        if (!result.ok && warningMessage != null) {
            result = ValidationResult.error(warningMessage);
        }
        return result;
    }
}
