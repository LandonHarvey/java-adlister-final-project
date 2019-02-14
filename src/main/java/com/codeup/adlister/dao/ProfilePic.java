package com.codeup.adlister.dao;

import com.codeup.adlister.models.profilePic;

import java.util.List;

public interface ProfilePic {
    List<profilePic> all();
    profilePic byUserId(Long user_id);
    void update(Long user_id, String fileHandler);
    Boolean insert(Long user_id, String fileHandler);
    Boolean delete(Long user_id);
}
