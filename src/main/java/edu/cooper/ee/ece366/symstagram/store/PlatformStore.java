package edu.cooper.ee.ece366.symstagram.store;


import edu.cooper.ee.ece366.symstagram.model.Post;
import edu.cooper.ee.ece366.symstagram.model.User;

import java.util.ArrayList;

public interface PlatformStore {
    public User createUser(String name, String password, String phone, String email);

    public User updateUser(User user, String name, String password, String phone);

    public Post createPost(String postText, User user);

    public Post sendPost(User user, User friend, String postText);

    public Boolean sendFriendRequest(User user, User friend);

    public ArrayList<String> getFriendRequests(User user);

    public Boolean acceptFriendRequest(User user, User friend);

    public ArrayList<String> getFriends(User user);

    public User getUser(String email);


}
