package com.actimind.diagnostic.validator;

import com.actimind.diagnostic.xml.AttributePattern;

@AttributePattern("shall-be-larger")
public class LargerValidator extends NumberValidator {

    public LargerValidator(String str) {
        super(str);
    }
}
