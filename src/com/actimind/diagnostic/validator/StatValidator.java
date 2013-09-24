package com.actimind.diagnostic.validator;

/**
 * Performs validation of the specified object and returns ValidationResult.
 *
 * Any class which implements this interface and will be created from monitor.xml
 * shall have public constructor with String parameter.
 * This parameter will contain string from monitor.xml 
 */
public interface StatValidator<T> {

    public ValidationResult validate(T value);

}
