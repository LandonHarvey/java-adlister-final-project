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
    private String username;
    private String offense_name;
    private String offender;
    private String title;
    private String created;
    private String description;

    public report(){}

    public report(Long id,Long user_id, Long offense, Long user_reported_id, Long comment_id, Long  ad_id,String description){
        this.id = id;
        this.user_id = user_id;
        this.offense =offense;
        this.user_reported_id = user_reported_id;
        this.comment_id = comment_id;
        this.ad_id = ad_id;
        this.description = description;
    }

    public report(Long id, String username,String offense_name, String description, String offender, String title, String created){
        this.id = id;
        this.username = username;
        this.offense_name = offense_name;
        this.description = description;
        this.offender = offender;
        this.title = title;
        this.created = created;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOffender() {
        return offender;
    }

    public void setOffender(String offender) {
        this.offender = offender;
    }

    public String getOffense_name() {
        return offense_name;
    }

    public void setOffense_name(String offense_name) {
        this.offense_name = offense_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
