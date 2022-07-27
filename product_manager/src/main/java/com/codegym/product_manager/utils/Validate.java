package com.codegym.product_manager.utils;

import java.util.regex.Pattern;

public class Validate {

    public static final String NUMBER_REGEX = "\\d+";
    private static final String USERNAME_PATTERN = "^([a-z0-9]){3,18}$";
    private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^*])(?!.*['\"`]).{6,}";
    private static final String FUllNAME_PATTERN = "^([A-Z]+[a-z]*[ ]?)+$";
    private static final String PHONE_REGEX = "^[0][1-9][0-9]{8,9}|[+84][1-9][0-9]{10}$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    private static final String Role = "(ADMIN)|(USER)";

    public static boolean isNumberValid(String number) {
        return Pattern.compile(NUMBER_REGEX).matcher(number).matches();
    }

    public static boolean isUsernameValid(String userName) {
        return Pattern.compile(USERNAME_PATTERN).matcher(userName).matches();
    }

    public static boolean isPasswordValid(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    public static boolean isFullNameValid(String name) {
        return Pattern.compile(FUllNAME_PATTERN).matcher(name).matches();
    }

    public static boolean isPhoneValid(String phone) {
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public static boolean isRoleValid(String role) {
        return Pattern.compile(Role).matcher(role).matches();
    }
}

