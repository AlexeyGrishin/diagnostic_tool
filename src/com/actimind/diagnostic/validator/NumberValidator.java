package com.actimind.diagnostic.validator;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;


@TextPattern("Shall be in range (:param)")
@AttributePattern("shall-be-in-range")
public class NumberValidator extends AbstractValidator<Number> {

    private long min = Long.MIN_VALUE;
    private long max = Long.MAX_VALUE;

    public NumberValidator(String str) {
        String[] parts = str.split(",");
        if (parts.length > 0)
            min = Long.parseLong(parts[0]);
        if (parts.length > 1)
            max = Long.parseLong(parts[1]);
    }

    public String doValidate(Number value) {
        long l = value.longValue();
        if (l > max)
            return "Is larger than " + max;
        if (l < min)
            return "Is smaller than " + min;
        return null;
    }
}
