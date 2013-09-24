package com.actimind.diagnostic.validator;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;

@TextPattern("Shall == %param")
@AttributePattern("shall-be-equal")
public class StringEquality extends AbstractValidator {

    private String str;

    public StringEquality(String str) {
        this.str = str.trim();
    }

    public String doValidate(Object value) {
        if (value == null || !value.toString().trim().equals(str)) {
            return "Expected '" + str + "' but actual is '" + value + "'";
        }
        return null;
    }
}
