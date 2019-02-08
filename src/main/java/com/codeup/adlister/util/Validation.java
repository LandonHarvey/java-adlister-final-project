package com.codeup.adlister.util;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

public class Validation {

    public static boolean stringlength(String input) {
       return input.length() < 5;
    }

    public static boolean emailCheck(String email) {
        return !email.contains("@") || !email.contains(".com");
    }

    public static boolean isExistingUser(String username) {
        User user = DaoFactory.getUsersDao().findByUsername(username);
        System.out.println("Username does not exist: " + (user == null));
        return user == null;
    }

    public static boolean isExistingEmail(String email) {
        User user = DaoFactory.getUsersDao().findByEmail(email);
        System.out.println("Username does not exist: " + (user == null));
        return user == null;
    }

}
