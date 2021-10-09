package com.chatterApp.models;

import java.util.Date;

public class Post {
    private String author;  // just String for now
    private Date creationDate;
    private String message;

    public Post(String author, Date creationDate, String message) {
        this.author = author;
        this.creationDate = creationDate;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
