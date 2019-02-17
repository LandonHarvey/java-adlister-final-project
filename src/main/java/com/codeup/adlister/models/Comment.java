package com.codeup.adlister.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Comment {
    private Long id;
    private Long user_id;
    private Long ad_id;
    private Long parent_comment_id;
    private String comment;
    private List<Comment> children;
    private String username;
    private LocalDateTime posted;

    public Comment(){}

    public Comment (Long user_id, Long ad_id, String comment, String username, Timestamp posted) {
        this.user_id = user_id;
        this.ad_id = ad_id;
        this.comment = comment;
        this.username = username;
        this.posted = posted.toLocalDateTime();
    }

    public Comment (Long user_id, Long ad_id, Long parent_comment_id, String comment, List<Comment> children, String username, Timestamp posted) {
        this.user_id = user_id;
        this.ad_id = ad_id;
        this.parent_comment_id = parent_comment_id;
        this.comment = comment;
        this.children = children;
        this.username = username;
        this.posted = posted.toLocalDateTime();
    }

    public Comment(Long user_id, Long ad_id, String comment, Timestamp posted){
        this.user_id = user_id;
        this.ad_id = ad_id;
        this.comment = comment;
        this.posted = posted.toLocalDateTime();
    }

    public Comment(Long user_id, Long ad_id, String comment){
        this.user_id = user_id;
        this.ad_id = ad_id;
        this.comment = comment;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getAd_id() {
        return ad_id;
    }

    public void setAd_id(Long ad_id) {
        this.ad_id = ad_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPosted() {
        return posted;
    }

    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public Long getParent_comment_id() {
        return parent_comment_id;
    }

    public void setParent_comment_id(Long parent_comment_id) {
        this.parent_comment_id = parent_comment_id;
    }
}
