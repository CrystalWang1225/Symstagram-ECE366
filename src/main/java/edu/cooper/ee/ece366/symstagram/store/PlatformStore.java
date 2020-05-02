package edu.cooper.ee.ece366.symstagram.store;


import edu.cooper.ee.ece366.symstagram.model.Post;
import edu.cooper.ee.ece366.symstagram.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PlatformStore {
    public User createUser(User user);
    public Post createPost(Post post, User user, User friend);
    public List<Post> getFeed(User user);
    //public User getUser(String email);

//    public User updateUser(User user, String name, String password, String phone);
//
//    public Post createPost(String postText, User user);
//
//    public Post sendPost(User user, User friend, String postText);
//
    public void sendFriendRequest(long userId, long friendId, LocalDateTime time);

    public List<Long> getFriendRequests(long userId);

    public Boolean acceptFriendRequest(long userId, long friendId);
//
    public List<Long> getFriends(long userId);
//
    public User getUser(String email);


}
