package com.codeup.adlister.dao.Interfaces;

import com.codeup.adlister.models.Comment;

import java.util.List;

public interface Comments {
    // get a list of all the comments
    List<Comment> all();
    // list all comments belonging to an ad
    List<Comment> allByAdId(Long ad_id);
    // insert a new comment
    boolean insert(Long user_id, Long ad_id ,String comment);
    // insert a new child comment
    boolean insert(Long user_id, Long ad_id, Long parent_comment_id, String comment);
    // update an existing ad
    void update(Long user_Id, Long ad_Id, String comment);
    // delete comment
    Boolean delete(Long user_id, Long ad_id);
}
