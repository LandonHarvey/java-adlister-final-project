package com.codeup.adlister.dao.Interfaces;

import com.codeup.adlister.models.commentVote;

import java.util.List;

public interface CommentVote {
    List<commentVote> all();
    List<commentVote> allByCommentId(Long ad_id);
    List<commentVote> allByUserId(Long user_id);
    Boolean insert(Long comment_id, Long userId, String direction);
    Boolean delete(Long comment_id, Long user_id);
    void update(Long comment_id, Long userId, String direction);
    long commentsupvoted(long user_id);
}
