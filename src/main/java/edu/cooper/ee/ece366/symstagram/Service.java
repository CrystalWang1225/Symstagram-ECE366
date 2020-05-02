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

    private Jdbi jdbi;
    private PlatformStore platformStore;

    Service(PlatformStore platformStore){
        this.platformStore = platformStore;
    }

    public User createUser(String name, String password, String phone, String email) {
        User user;
        user = new User(name, password, phone, email);
        return platformStore.createUser(user);
    }

    public User updateUser(User user, String name, String password, String phone){

        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        return user;
    }


    public Post sendPost(String postText, User user, User receiver){
        Post post;
        post = new Post(postText,user);
        for (int i = 0; i < friends.size(); i++){
            if (receiver.getID() == friends.get(i)){
                return platformStore.createPost(post,user, receiver);
            }
        }
        System.out.println("The receiver is not your friend!!");
        return new Post("Nothing", null);
    }


  /*

   public Post sendPost(User user, User friend, String postText){
        Post post = createPost(postText, user);
        if (user != null && friend != null){
            friend.addToPostLists(post);
        }
       return post;
    }

/*

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
       return platformStore.getUser(email);
    }

 */

}

    public User getUser(String userEmail){
        return platformStore.getUser(userEmail);
    }