package com.codeup.adlister.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private LocalDateTime created;
    private List<String> categories;
    private long upvote;
    private long downvote;

    public Ad(long id, long userId, String title, String description, Timestamp created , List<String> categories, long upvote, long downvote) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.created = created.toLocalDateTime();
        this.categories = categories;
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public Ad(long id, long userId, String title, String description, Timestamp created , List<String> categories) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.created = created.toLocalDateTime();
        this.categories = categories;
    }

    public Ad(long userId, String title, String description, Timestamp created) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.created = created.toLocalDateTime();
    }

    public Ad(long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public long getUpvote() {
        return upvote;
    }

    public void setUpvote(long upvote) {
        this.upvote = upvote;
    }

    public long getDownvote() {
        return downvote;
    }

    public void setDownvote(long downvote) {
        this.downvote = downvote;
    }
}
