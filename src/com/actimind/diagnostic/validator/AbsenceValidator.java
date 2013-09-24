package com.actimind.diagnostic.validator;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;

/**
 * @see com.actimind.diagnostic.validator.PresenceValidator
 */
@TextPattern("Shall absent")
@AttributePattern("shall-absent")
public class AbsenceValidator extends AbstractValidator {

    private String msg = "is present, but it should not";

    public AbsenceValidator(String msg) {
        if (msg != null && !msg.isEmpty()) this.msg = msg;
    }

    public String doValidate(Object value) {
        if (!PresenceCheck.isPresent(value))
            return null;
        return msg;
    }

}
