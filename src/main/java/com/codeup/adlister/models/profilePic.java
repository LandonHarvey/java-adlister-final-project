package com.codeup.adlister.models;

public class profilePic {
    private Long user_id;
    private String fileHandler;

    public profilePic(){}

    public profilePic(Long user_id, String fileHandler) {
        this.user_id = user_id;
        this.fileHandler = fileHandler;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFileHandler() {
        return fileHandler;
    }

    public void setFileHandler(String fileHandler) {
        this.fileHandler = fileHandler;
    }
}
