package com.actimind.diagnostic.validator;

public abstract class AbstractValidator<T> implements StatValidator<T> {

    public final ValidationResult validate(T value) {
        return ValidationResult.okIfNull(doValidate(value));

    }

    protected abstract String doValidate(T value);
}
