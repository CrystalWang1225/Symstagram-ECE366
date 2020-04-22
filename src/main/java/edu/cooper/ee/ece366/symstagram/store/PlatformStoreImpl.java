package edu.cooper.ee.ece366.symstagram.store;

import edu.cooper.ee.ece366.symstagram.model.Post;
import edu.cooper.ee.ece366.symstagram.model.User;

import java.sql.Connection;
import java.util.ArrayList;

import org.jdbi.v3.core.Jdbi;

import java.time.LocalDateTime;


public class PlatformStoreImpl implements PlatformStore {

    private final Jdbi jdbi;

    public PlatformStoreImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }



    public void populateDb(){
        jdbi.withHandle(
                handle ->
                    handle.execute(
                            "create table if not exists users (id bigint auto_increment, name varchar(255), password varchar(255), email varchar(255), phone varchar(255), primary key(id));"));
//        handle.execute("create table if not exists posts (id bigint auto_increment, postText varchar(255), userEmail varchar(255), date datetime, primary key(id));");
    }
    public User createUser(User user){
        Integer id = jdbi.withHandle(
                handle ->
                        handle.createUpdate("INSERT INTO users (name, password, phone, email) values (:name, :password, :phone, :email)")
                                .bind("name", user.getName())
                                .bind("password", user.getPassword())
                                .bind("phone", user.getPhone())
                                .bind("email", user.getEmail())
                                .executeAndReturnGeneratedKeys("id")
                                .mapTo(Integer.class)
                                .one());
        user.setID(id);
        return user;

    }
//
//    public User updateUser(User user, String name, String password, String phone) {
//
//    }
//
//    public Post createPost(Post post, User user){
//        Integer id = jdbi.withHandle(
//           handle ->
//                    handle.createUpdate("INSERT INTO posts (postText, userEmail, date) values (:postText, :userEmail, :date)")
//                       .bind("postText", post.getPostText())
//                       .bind("userEmail", user.getEmail())
//                       .bind("date", post.getDate())
//                        .executeAndReturnGeneratedKeys("id")
//                    .mapTo(Integer.class)
//                    .one()
//        );
//
//        post.setId(Integer.toString(id));
//        return post;
//    }

//    public Post sendPost(User user, User friend, String postText){
//
//    }
//
//    public Boolean sendFriendRequest(User user, User friend) {};
//
//    public ArrayList<String> getFriendRequests(User user);
//
//    public Boolean acceptFriendRequest(User user, User friend);
//
//    public ArrayList<String> getFriends(User user){
//       return jdbi.withHandle(
//                handle ->
//                        handle.createQuery("SELECT id, name,email from users ")
//                .mapToBean(User.class)
//                .list()
//
//        );
//    }
//
//    public User getUser(String email){
//        User user = jdbi.withHandle(
//                handle ->
//                        handle.select("select id, name, password, phone, email", email)
//                                .mapToBean(User.class)
//                                .one());
//        return user;
//    }
}
