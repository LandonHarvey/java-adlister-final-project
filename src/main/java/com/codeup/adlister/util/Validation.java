package com.codeup.adlister.util;

public class Validation {
    public static boolean stringlength(String input) {
       return input.length() < 5;
    }

    public static String titleLength (String title) {
        if (title.equals("")) {
            return "Must input Title";
        }else {
            return "Length Good";
        }
    }
    public static String desLength (String des) {
        if (des.equals("")) {
            return "Must Input Description";
        }else {
            return "Length Good";
        }
    }

    public static boolean emailCheck(String email) {
        return !email.contains("@") || !email.contains(".com");
    }

    public static boolean titleSet(String title) {
        return title != null;
    }

    public static boolean desSet(String des) {
        return des != null;
    }
}
