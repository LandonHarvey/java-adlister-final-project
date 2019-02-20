package com.codeup.adlister.models;

public class offense {
    private Long id;
    private String offense_name;

    public offense(){}

    public offense(String offense_name){
        this.offense_name = offense_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOffense_name() {
        return offense_name;
    }

    public void setOffense_name(String offense_name) {
        this.offense_name = offense_name;
    }
}
