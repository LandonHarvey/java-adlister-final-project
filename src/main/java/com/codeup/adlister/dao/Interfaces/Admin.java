package com.codeup.adlister.dao.Interfaces;

import com.codeup.adlister.models.admin;

import java.util.List;

public interface Admin {
    // get a list of all the ads
    List<admin> all();
    // insert a new admin and return the new admins id
    Long insert(long user_id, long jedimaster,  String level);
    // update an existing admin
    void update(admin admin);
    //update admin level
    void updateLevel(long jedimaster, long user_id, String level);
}
