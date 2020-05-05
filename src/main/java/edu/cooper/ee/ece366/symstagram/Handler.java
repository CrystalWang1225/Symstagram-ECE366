package edu.cooper.ee.ece366.symstagram;

import edu.cooper.ee.ece366.symstagram.model.Post;
import edu.cooper.ee.ece366.symstagram.model.User;
import edu.cooper.ee.ece366.symstagram.Service;
import spark.Request;
import spark.Response;

import java.lang.reflect.Array;
import java.util.*;


public class Handler {
    private final Service service;

    HashMap<String, User> userSet = new HashMap<String, User>();
    //  HashMap<String,Post> postSet = new HashMap<String, Post>();

    public Handler(Service service){
        this.service = service;
    }

    public static void UpdateResponse(Response response, Integer code, String message) {
        response.status(code);
        response.body(message);
        System.out.println(message);
    }

    public Boolean Register(Request request, Response response) {
        String name =  request.queryParams("name");
        String password =  request.queryParams("password");
        String phone =  request.queryParams("phone");
        String email =  request.queryParams("email");

        if(service.getUser(email).isPresent()) {
            UpdateResponse(response,401, "User with that email already exists");
            return false;
        }

        else {
            User user = service.createUser(name, password, phone, email);
            UpdateResponse(response, 200, "Account creation successful");
            return true;
        }
    }

    public boolean sendPost(Request request, Response response){
        Optional<User> user = service.getUser(request.queryParams("email"));
        Optional<User> friend = service .getUser(request.queryParams("friendEmail"));

        if(user.isEmpty() || friend.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }

        else {
            Post post = service.sendPost(request.queryParams("postText"), user.get(), friend.get());
            UpdateResponse(response, 200, post.getPostText());
            return true;
        }
    }

    public Boolean Login(Request request, Response response) {
        String password = request.queryParams("password");
        String email = request.queryParams("email");

        Optional<User> user = service.getUser(email);

        if(password.isEmpty() || email.isEmpty()) {
            UpdateResponse(response,401, "Fields are blank");
            return false;
        }

        if(user.isEmpty()) {
            UpdateResponse(response,401, "User does not exist");
            return false;
        }

        if(!user.get().getPassword().equals(password)) {
            UpdateResponse(response,401, "Wrong password");
            return false;
        }
        else {
            UpdateResponse(response,200, "Login successful");
            return true;
        }

    }

    public Boolean SendFriendRequest(Request request, Response response) {
        Optional<User> user = service.getUser(request.queryParams("email"));
        Optional<User> friend = service.getUser(request.queryParams("friendemail"));
        List<Long> friendPendingFriendRequests = service.getFriendRequests(friend.get());
        List<Long> userFriends = service.getFriends(user.get());

        if (user.isEmpty() || friend.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }

        if (friendPendingFriendRequests.contains(user.get().getID())) {
            UpdateResponse(response,401, "Friend request already sent");
            return false;
        }

        if (userFriends.contains(friend.get().getID())) {
            UpdateResponse(response,401, "Already friends with " + friend.get().getName());
            return false;
        }

        else {
            if(service.sendFriendRequest(user.get(), friend.get())) {
                UpdateResponse(response, 200, "Friend request sent to " + friend.get().getName());
                return true;
           }
            else {
                UpdateResponse(response, 401, "Friend request failed to send");
                return false;
            }
        }
    }

    public Boolean GetFriendRequests (Request request, Response response) {
        Optional<User> user = service.getUser(request.queryParams("email"));

        if (user.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }
        else {
            System.out.println(service.getFriendRequests(user.get()));
            UpdateResponse(response, 200, "List of pending friend requests successfully retrieved");
            return true;
        }

    }

    public Boolean AcceptFriendRequest(Request request, Response response) {
        Optional<User> user = service.getUser(request.queryParams("email"));
        Optional<User> friend = service.getUser(request.queryParams("friendemail"));
        List<Long> userFriendRequests = service.getFriendRequests(user.get());

        if (user.isEmpty() || friend.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }

        if(userFriendRequests.contains(friend.get().getID())) {
            service.acceptFriendRequest(user.get(),friend.get());
            UpdateResponse(response,401, "Friend request accepted");
            return true;
        }
        else {
            UpdateResponse(response,401, "Friend request failed to be accepted");
            return false;
        }
    }

    public Boolean GetFriends (Request request, Response response) {
        Optional<User> user = service.getUser(request.queryParams("email"));
        if (user.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }
        else {
            UpdateResponse(response, 200, "List of friends successfully retrieved: " + service.getFriends(user.get()).toString());
            return true;
        }
    }

    public Boolean editInfo(Request request, Response response){
        Optional<User> user =service.getUser(request.queryParams("email"));

        if(user.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }
        else {
            service.updateUser(
                    user.get(),
                    request.queryParams("newName"),
                    request.queryParams("newPassword"),
                    request.queryParams("newPhone"));
            UpdateResponse(response,200, "Account updated successfully for " + user.get().getName());
            return true;
        }
    }




}
