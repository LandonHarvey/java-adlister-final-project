package com.codeup.adlister.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class report {
    private Long id;
    private Long user_id;
    private Long offense;
    private Long user_reported_id;
    private Long ad_id;
    private Long comment_id;
    private String description;
    private LocalDateTime created;

    public report(){}

    public report(Long user_id, Long offense, Long user_reported_id, String description){
        this.user_id = user_id;
        this.offense =offense;
        this.user_reported_id = user_reported_id;
        this.description = description;
    }

    public report(Long user_id, Long offense, String description, Long ad_id){
        this.user_id = user_id;
        this.offense = offense;
        this.ad_id = ad_id;
        this.description = description;
    }

    public report( String description, Long user_id, Long offense, Long comment_id){
        this.user_id = user_id;
        this.offense = offense;
        this.comment_id = comment_id;
        this.description = description;
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

    public Long getOffense() {
        return offense;
    }

    public void setOffense(Long offense) {
        this.offense = offense;
    }

    public Long getUser_reported_id() {
        return user_reported_id;
    }

    public void setUser_reported_id(Long user_reported_id) {
        this.user_reported_id = user_reported_id;
    }

    public Long getAd_id() {
        return ad_id;
    }

    public void setAd_id(Long ad_id) {
        this.ad_id = ad_id;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
