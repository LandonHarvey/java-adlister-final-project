package com.codeup.adlister.dao.Interfaces;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    User findByEmail(String email);
    Long insert(User user);
    User findByAdId(String ad_id);
    User findById(long id);
    void update(User user);
    boolean delete(Long id);
    User findByCommentId (Long comment_id);
}
