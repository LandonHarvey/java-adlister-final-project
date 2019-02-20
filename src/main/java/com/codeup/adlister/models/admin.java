package com.codeup.adlister.models;

import com.codeup.adlister.util.Password;

import java.time.LocalDateTime;

public class admin {
    private long id;
    private long jedimaster;
    private long user_id;
    private String password;
    private LocalDateTime created;
    private String level;

    public admin (){};

    public admin (long jedimaster, long user_id, String level, String password){
        this.jedimaster = jedimaster;
        this.user_id = user_id;
        this.level = level;
        setPassword(password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJedimaster() {
        return jedimaster;
    }

    public void setJedimaster(long jedimaster) {
        this.jedimaster = jedimaster;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Password.hash(password);
    }
}
