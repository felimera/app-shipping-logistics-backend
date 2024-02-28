package com.project.appshippinglogistics.util;

public class Constants {
    private Constants() {
        throw new IllegalStateException(Constants.class.toString());
    }

    public static final String AUTHORIZATION = "Authorization";
    public static final String SPACE = " ";
    public static final String BEARER = "Bearer".concat(SPACE);

    public static final String MESSAGE_NOT_FOUND = "No results were found.";
    public static final String MESSAGE_USER_NOT_FOUND = "The user does not exist.";
}
