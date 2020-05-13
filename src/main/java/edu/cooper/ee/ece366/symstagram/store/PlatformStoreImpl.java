

package edu.cooper.ee.ece366.symstagram.store;
 import edu.cooper.ee.ece366.symstagram.model.Post;
 import edu.cooper.ee.ece366.symstagram.model.User;

 import org.jdbi.v3.core.Jdbi;

 import java.time.LocalDateTime;
 import java.util.List;
 import java.util.Optional;


public class PlatformStoreImpl implements PlatformStore {

    private final Jdbi jdbi;

    public PlatformStoreImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }



    public void populateDb(){
        jdbi.useHandle(
                handle -> {
                    handle.execute(
                            "create table if not exists users (id bigint auto_increment, name varchar(255), password varchar(255), email varchar(255), phone varchar(255),primary key(id), friends json, postLists json);");
                    handle.execute("create table if not exists posts (postId bigint auto_increment, postText varchar(255), senderId bigint, receiverId bigint, date datetime, primary key(postId));");
                    handle.execute("create table if not exists userrelations (firstUserId varchar(255), secondUserId varchar(255), relationship varchar(255), date datetime, primary key(firstUserId, secondUserId));");
                }
        );
    }


    public User createUser(User user){
        //  String friendJson = new Gson().toJson(user.getFriends());
        // String postJson = new Gson().toJson(user.getPostLists());


        Integer id = jdbi.withHandle(
                handle ->
                        handle.createUpdate("INSERT INTO users (name, password, phone, email) values (:name, :password, :phone, :email)")
                                .bind("name", user.getName())
                                .bind("password", user.getPassword())
                                .bind("phone", user.getPhone())
                                .bind("email", user.getEmail())
                                //   .bind("friends", friendJson)
                                // .bind("postLists", postJson)
                                .executeAndReturnGeneratedKeys("id")
                                .mapTo(Integer.class)
                                .one());
        user.setID(id);

//TODO: implement this conversion wherever necessary
//        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
//        ArrayList<String> friends = new Gson().fromJson(friendJson, listType);

        return user;
    }


    public User updateUser(User user, String name, String password, String phone, String email) {
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        Integer id1 = jdbi.withHandle(
                handle ->
                        handle.createUpdate("UPDATE users SET name = :name, password = :password, phone = :phone WHERE email = :email")

                                .bind("name", user.getName())
                                .bind("password", user.getPassword())
                                .bind("phone", user.getPhone())
                                .bind("email", email)
                                .execute());
        return user;


    }




    public Optional<User> getUser(String email){
         Optional<User> user = jdbi.withHandle(
                handle ->
                        handle.select("select id, name, password, phone, email from users where email = ?", email)
                                .mapToBean(User.class)
                                .findOne());
        return user;
    }


    public List<Post> getFeed(User user){
        return jdbi.withHandle(
                handle ->
                        handle.select("SELECT postId, postText, senderId, receiverId, date FROM posts where receiverId = ?", user.getID())
                                .mapToBean(Post.class)
                                .list()
        );

    }

    public Post createPost(Post post, User user, User friend){
        Integer id = jdbi.withHandle(
                handle ->
                        handle.createUpdate("INSERT INTO posts (postText, senderId, receiverId, date) values (:postText, :senderId, :receiverId, :date)")
                                .bind("postText", post.getPostText())
                                .bind("senderId", user.getID())
                                .bind("receiverId", friend.getID())
                                .bind("date", post.getDate())
                                .executeAndReturnGeneratedKeys("id")
                                .mapTo(Integer.class)
                                .one()
        );

        post.setPostId(new Long(id));
        return post;
    }

    public Post getPost(Long id, User user, User friend){
        return jdbi.withHandle(
                handle ->
                        handle.createQuery("SELECT * FROM posts where senderId = :senderId AND receiverId = :receiverId")
                                .bind("senderId", user.getID())
                                .bind("receiverId", friend.getID())
                                .mapToBean(Post.class)
                                .one()
        );

    }





    public Boolean rejectFriendRequest(long userId, long friendId) {
        long firstUserId;
        if (userId > friendId)
            firstUserId = userId;
        else
            firstUserId = friendId;


        jdbi.useHandle(handle -> {
            handle.createUpdate("UPDATE userrelations SET relationship = :relationship WHERE firstuserid = :userId")
                    .bind("relationship", 4)
                    .bind("userId", firstUserId)
                    .execute();
        });

        return true;
    };
    //firstuserid > seconduserid ALWAYS
    /* Tentative relationship types:
    1: friends
    2: pending friend request from firstuserid to seconduserid
    3: pending friend request from seconduserid to firstuserid
     */
    public Boolean sendFriendRequest(long userID, long friendID, LocalDateTime time) {
        if (userID > friendID) {
            try {
                jdbi.useHandle(handle -> {
                    handle.execute("INSERT INTO userrelations (firstuserid, seconduserid, relationship, date) values (?, ?, ?, ?)", userID, friendID, 2, time);
                });
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
        else {
            try {
                jdbi.useHandle(handle -> {
                    handle.execute("INSERT INTO userrelations (firstuserid, seconduserid, relationship, date) values (?, ?, ?, ?)", friendID, userID, 3, time);
                });
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
    };

    public List<Long> getFriendRequests(long userId) {
        List<Long> list1 = jdbi.withHandle(
                handle ->
                        handle.createQuery("SELECT secondUserId FROM userrelations where firstUserId = :userId AND relationship = :relationship")
                                .bind("userId", userId)
                                .bind("relationship", 3)
                                .mapTo(Long.class)
                                .list());

        List<Long> list2 = jdbi.withHandle(
                handle ->
                        handle.createQuery("SELECT firstUserId FROM userrelations where secondUserId = :userId AND relationship = :relationship")
                                .bind("userId", userId)
                                .bind("relationship", 2)
                                .mapTo(Long.class)
                                .list());

        list1.addAll(list2);
        return list1;
    };

    public Boolean acceptFriendRequest(long userId, long friendId) {
        long firstUserId;
        if (userId > friendId)
            firstUserId = userId;
        else
            firstUserId = friendId;


        jdbi.useHandle(handle -> {
            handle.createUpdate("UPDATE userrelations SET relationship = :relationship WHERE firstuserid = :userId")
                    .bind("relationship", 1)
                    .bind("userId", firstUserId)
                    .execute();
        });

        return true;
    };

    public List<Long> getFriends(long userId){
        List<Long> list1 = jdbi.withHandle(
                handle ->
                        handle.createQuery("SELECT secondUserId FROM userrelations where firstUserId = :userId AND relationship = :relationship")
                                .bind("userId", userId)
                                .bind("relationship", 1)
                                .mapTo(Long.class)
                                .list());

        List<Long> list2 = jdbi.withHandle(
                handle ->
                        handle.createQuery("SELECT firstUserId FROM userrelations where secondUserId = :userId AND relationship = :relationship")
                                .bind("userId", userId)
                                .bind("relationship", 1)
                                .mapTo(Long.class)
                                .list());
        list1.addAll(list2);
        return list1;

    }


/*
    public void sendPost(User user, User friend, String postText){
    }
*/

    /*
    public Boolean sendFriendRequest(User user, User friend) {};
    public ArrayList<String> getFriendRequests(User user);
    public Boolean acceptFriendRequest(User user, User friend);
    public ArrayList<String> getFriends(User user){
       return jdbi.withHandle(
                handle ->
                        handle.createQuery("SELECT id, name,email from users ")
                .mapToBean(User.class)
                .list()
        );
    }
*/




}
