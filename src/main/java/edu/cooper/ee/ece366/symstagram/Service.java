package edu.cooper.ee.ece366.symstagram;

import edu.cooper.ee.ece366.symstagram.model.User;
import edu.cooper.ee.ece366.symstagram.model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import edu.cooper.ee.ece366.symstagram.store.PlatformStore;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.core.result.ResultIterator;
import org.jdbi.v3.core.statement.Query;

public class Service {

    private final PlatformStore platformStore;

    public Service(PlatformStore platformStore){
        this.platformStore = platformStore;
    }

    public User createUser(User user){
        return platformStore.createUser(user);
    }




   /* private Jdbi jdbi;

    Service(Jdbi jdbi){
        this.jdbi = jdbi;
    }
    /*
    public User createUser(String name, String password, String phone, String email) {
        User user;
        user = new User(name, password, phone, email);
        return user;
    }
    public User updateUser(User user, String name, String password, String phone){
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        return user;
    }


    public Post createPost(String postText, User user){
    Post post;
    String id = Calendar.getInstance().toString();
    post = new Post(postText,user,id);
    return post;
  }

   public Post sendPost(User user, User friend, String postText){
        Post post = createPost(postText, user);
        if (user != null && friend != null){
            friend.addToPostLists(post);
        }
       return post;
    }



    public Boolean sendFriendRequest(User user, User friend) {
        if(user != null && friend != null) {
            friend.addToFriendRequests(user.getEmail());
            return true;
        }
        else
            return false;
    }

    public ArrayList<String> getFriendRequests(User user) {
        return user.getFriendRequests();
    }

    public Boolean acceptFriendRequest(User user, User friend) {
        if(user.getFriendRequests().contains(friend.getEmail())) {
            user.removeFromFriendRequests(friend.getEmail());
            user.addToFriends(friend.getEmail());
            friend.addToFriends(user.getEmail());
            return true;
        }

        else
            return false;
    }

    public ArrayList<String> getFriends(User user) {
        return user.getFriends();
    }
    public User getUser(String email){
        User user = jdbi.withHandle(
                handle ->
                        handle.select("select id, name, password, phone, email", email)
                                .mapToBean(User.class)
                                .one());
        return user;
    }
     */




}

