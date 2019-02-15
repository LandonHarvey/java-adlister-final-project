package com.codeup.adlister.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Long user_id;
    private Long ad_id;
    private String comment;
    private LocalDateTime posted;

    public Comment(){}

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
}
