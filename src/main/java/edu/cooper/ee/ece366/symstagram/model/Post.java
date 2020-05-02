package edu.cooper.ee.ece366.symstagram.model;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class Post {
    @Expose
    private String postText;
    @Expose
    private LocalDateTime date;
    @Expose
    private User user;
    private Integer id;

    public Post(String postText, User user, LocalDateTime date, Integer id ){
        this.postText = postText;
        this.date = date;
        this.user = user;
        this.id = id;
    }

    public Post(String postText, User user){
        this.postText = postText;
        this.user = user;
        this.date = LocalDateTime.now();
        this.id = -1;
    }

    public Post(){
        this.postText = null;
        this.date = LocalDateTime.now();
        this.user = null;
        this.id =  null;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Post(String postText) {
        this.postText = postText;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


