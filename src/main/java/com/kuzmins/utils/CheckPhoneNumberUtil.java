package com.kuzmins.utils;

public final class CheckPhoneNumberUtil {

    private CheckPhoneNumberUtil(){}

    public static String checkPhoneNumber(String phoneNumber) {
        String number = phoneNumber;
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            number = number.replace(" ", "").
                    replace("(", "").
                    replace(")", "").
                    replace("-", "").
                    replace("+", "");
        } else {
            throw new IllegalArgumentException("Phone number shouldn't be empty");
        }

        if (number.matches("^\\d+")){
            return phoneNumber;
        } else throw new IllegalArgumentException("Phone number should consist of no more than 15 digits only");
    }
}
