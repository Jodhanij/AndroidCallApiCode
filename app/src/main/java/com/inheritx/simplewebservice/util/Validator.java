package com.inheritx.simplewebservice.util;

/**
 * Created by Samir Bhatt on 4/28/2016.
 *
 * Common class for validation
 */
public class Validator {
    private static Validator mValidator;

    private Validator() {

    }

    public static Validator getmValidator() {
        if (mValidator == null) {
            mValidator = new Validator();
        }
        return mValidator;
    }

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public boolean isStringMatch(String tempString1, String tempString2) {
        if (tempString1.equalsIgnoreCase(tempString2)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmptyText(String text) {
        if (text == null) {
            return true;
        } else {
            if (text.trim().length() == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
