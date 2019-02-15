package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    User findByEmail(String email);
    Long insert(User user);
    User findByAdId(String ad_id);
    void update(User user);
    User findByCommentId (Long comment_id);
}
