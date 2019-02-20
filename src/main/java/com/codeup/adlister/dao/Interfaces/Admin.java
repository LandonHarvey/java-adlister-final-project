package com.codeup.adlister.dao.Interfaces;

import com.codeup.adlister.models.admin;

import java.util.List;

public interface Admin {
    // get a list of all the ads
    List<admin> all();
    // get whether user is admin
    admin isAdmin(long id);
    // insert a new admin and return the new admins id
    boolean insert(long user_id, long jedimaster,  String level);
    // delete admin rights
    void delete(String id);
    // update an existing admin
    void update(admin admin);
    // update admin level
    boolean updateLevel(long jedimaster, long user_id, String level);
}
