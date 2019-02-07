package com.codeup.adlister.models;

public class AdError {
    private String error;

    public AdError(){}

    public AdError (String error) {
        this.error = error;
    }

    public String getError () {
        return error;
    }

    public String setError (String e) {
        return this.error = e;
    }
}
