package edu.cooper.ee.ece366.symstagram.store;

import com.google.gson.Gson;
import edu.cooper.ee.ece366.symstagram.model.Post;
import edu.cooper.ee.ece366.symstagram.model.User;

import java.util.ArrayList;

import org.jdbi.v3.core.Jdbi;

import java.time.LocalDateTime;


public class PlatformStoreImpl implements PlatformStore {

    private final Jdbi jdbi;

    public PlatformStoreImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }



    public void populateDb(){
        jdbi.useHandle(
                handle -> {
        handle.execute("Create a table if not exists users (id bigint auto_increment, name varchar(255), password varchar(255), email varchar(255), phone varchar(255), primary key(id),friends json);");
        handle.execute("Create a table if not exists posts (id bigint auto_increment, postText varchar(255), userEmail varchar(255), friendEmail varchar(255), date datetime, primary key(id));");
    });

    }
    public User createUser(User user){
        String friendJson = new Gson().toJson(user.getFriends());
        Integer id = jdbi.withHandle(
                handle ->
                        handle.createUpdate("INSERT INTO users (name, password, phone, email) values (:name, :password, :phone, :email)")
                                .bind("name", user.getName())
                                .bind("password", user.getPassword())
                                .bind("phone", user.getPhone())
                                .bind("email", user.getEmail())
                                .bind("friends", friendJson)
                                .executeAndReturnGeneratedKeys("id")
                                .mapTo(Integer.class)
                                .one());
        user.setID(id);
        return user;

    }

    public User updateUser(User user, String name, String email, String password, String phone){

        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        Integer id1 = jdbi.withHandle(
                handle ->
                        handle.createQuery("Select name from users where email = :email")
                                .bind("email", email)
                                .bind("name", user.getName())
                                .mapTo(Integer.class)
                                .one());
        Integer id2 = jdbi.withHandle(
                handle ->
                        handle.createQuery("Select password from users where email = :email")
                                .bind("email", email)
                                .bind("password", user.getPassword())
                                .mapTo(Integer.class)
                                .one());
        Integer id3 = jdbi.withHandle(
                handle ->
                        handle.createQuery("Select phone from users where email = :email")
                                .bind("email", email)
                                .bind("phone", user.getPhone())
                                .mapTo(Integer.class)
                                .one());
        return user;
    }



    public Post createPost(Post post, User user){
        Integer id = jdbi.withHandle(
           handle ->
                    handle.createUpdate("INSERT INTO posts (postText, userEmail, friendEmail,date) values (:postText, :userEmail, :friendEmail, :date)")
                       .bind("postText", post.getPostText())
                       .bind("userEmail", user.getEmail())
                            .bind("friendEmail", user.getFriends())
                       .bind("date", post.getDate())
                        .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one()
        );

        post.setId(Integer.toString(id));
        return post;
    }

    public void sendPost(User user, User friend, Post post){
       jdbi.withHandle(
                handle ->
                        handle.execute("INSERT INTO posts (postText, userEmail, friendEmail date) values(?, ?, ?,?)", post.getPostText(), user.getEmail(), friend.getEmail(), post.getDate())
        );

       return;
    }

    /*

    public Boolean sendFriendRequest(User user, User friend);

    public ArrayList<String> getFriendRequests(User user);

    public Boolean acceptFriendRequest(User user, User friend);

    public ArrayList<String> getFriends(User user){


    }

     */

    public User getUser(String email){
        User user = jdbi.withHandle(
                handle ->
                        handle.select("select id, name, password, phone, email", email)
                                .mapToBean(User.class)
                                .one());
        return user;
    }
}
