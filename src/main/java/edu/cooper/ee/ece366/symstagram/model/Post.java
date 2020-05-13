package edu.cooper.ee.ece366.symstagram.model;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class Post {
    @Expose
    private String postText;
    @Expose
    private LocalDateTime date;
    @Expose
    private Long senderId ;
    private Long receiverId;
    private Long postId;


    public Post(Long postId, String postText, Long senderId, Long receiverId){
        this.postId = postId;
        this.postText = postText;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public Post(String postText, User user){
        Long postId = new Long(-1);
        this.postId = postId;
        this.postText = postText;
        this.senderId = user.getID();
    }

    public Post(){
        this.postId = null;
        this.postText = null;
        this.senderId = null;
        this.receiverId = null;
        this.date = LocalDateTime.now();
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

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}


