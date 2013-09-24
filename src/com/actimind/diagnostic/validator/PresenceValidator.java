package com.actimind.diagnostic.validator;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;


/**
 * Could be used for any stat collector which:
 *  * return true if object is present and false - if absent
 *  * return >0 if object is present and 0 - if absent
 *  * return string if object is present and empty string - if absent
 *  * return null if object is absence
 *
 * Its parameter will be shown as a message
 */
@TextPattern("Shall present")
@AttributePattern("shall-present")
public class PresenceValidator extends AbstractValidator {

    private String msg = "is absent";

    public PresenceValidator(String msg) {

    }

    public String doValidate(Object value) {
        if (PresenceCheck.isPresent(value))
            return null;
        return msg;
    }
}
