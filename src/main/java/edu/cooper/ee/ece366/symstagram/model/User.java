package edu.cooper.ee.ece366.symstagram.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class User {
    @Expose private String name;
    private String password;
    @Expose private String phone;
    @Expose private String email;
    private long id;

    private ArrayList<String> friendrequests = new ArrayList<String>();
    private ArrayList<String> friends = new ArrayList<String>();
    private ArrayList<Post> postlists = new ArrayList<Post>();
    public ArrayList<Post> getPostlists() {
        return postlists;
    }

    public void setPostlists(ArrayList<Post> postlists) {
        this.postlists = postlists;
    }

    //private final List organizations;
    public User(long id, String name, String password, String phone, String email){
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    };

    public User(String name, String password, String phone, String email) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public User(){
        this.id = -1;
        this.name = null;
        this.password = null;
        this.phone = null;
        this.email =  null;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getPhone() { return phone; }
    public ArrayList<String> getFriendRequests() {return friendrequests;}
    public ArrayList<String> getFriends() {return friends;}

    public void setID(long id) { this.id = id; }
    public long getID() {return id;}

    public void setName(String name){this.name = name;}
    public void setPassword(String password){this.password = password; }
    public void setPhone(String phone){this.phone = phone;}
    public void setEmail(String email){this.email = email;}

    public void addToFriendRequests(String email) {
        friendrequests.add(email);
    }


    public void addToPostLists(Post post){
        postlists.add(post);
    }

    public void removeFromFriendRequests(String email) {
        friendrequests.remove(email);
    }

    public void addToFriends(String email) {
        friends.add(email);
    }



    public void removeFromFriends(String email) {
        friends.remove(email);
    }


}