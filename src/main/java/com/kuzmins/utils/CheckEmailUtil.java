package com.kuzmins.utils;

public final class CheckEmailUtil {

    private CheckEmailUtil(){}

    public static String checkEmail(String email) {
        if (email != null && email.matches("^[\\w-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$")) {
            return email;
        } else {
            throw new IllegalArgumentException("Wrong email format");
        }
    }
}
