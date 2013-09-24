package com.actimind.diagnostic.validator;

import com.actimind.diagnostic.xml.AttributePattern;
import com.actimind.diagnostic.xml.TextPattern;

/**
 * Works in pair with
 * @see com.actimind.diagnostic.stats.HTTPResponseCodeObtainer
 *
 * Expects that codes 2xx are normal, all other and Timeout - are not
 */
@TextPattern("HTTP Response shall be OK")
@AttributePattern("shall-be-200-ok")
public class HTTPResponseCodeChecker extends AbstractValidator {


    public HTTPResponseCodeChecker(String s) {

    }

    public String doValidate(Object value) {
        if (value instanceof Number) {
            return validateResponseCode((Number)value);
        }
        return "Timeout occurred";
    }

    private String validateResponseCode(Number number) {
        int rcode = number.intValue();
        if (rcode >= 200 && rcode <= 299) {
            return null;
        }
        return "Unexpected HTTP response";
    }
}
