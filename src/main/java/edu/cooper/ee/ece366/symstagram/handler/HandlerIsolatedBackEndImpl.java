package edu.cooper.ee.ece366.symstagram.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.cooper.ee.ece366.symstagram.model.Post;
import edu.cooper.ee.ece366.symstagram.model.User;
import edu.cooper.ee.ece366.symstagram.Service;
import spark.Request;
import spark.Response;

import java.util.*;

public class HandlerIsolatedBackEndImpl implements Handler{
    private final Service service;

    public HandlerIsolatedBackEndImpl(Service service) {
        this.service = service;
    }

    public static void UpdateResponse(Response response, Integer code, String message) {
        response.status(code);
        response.body(message);
        System.out.println(message);
    }

    @Override
    /*
    public Boolean getUser(Request request, Response response){
        JsonObject reqObj = new Gson().fromJson(request.body(), JsonObject.class);

        String email = reqObj.get("email").getAsString();
        if (email.isEmpty()){
            UpdateResponse(response,401, "Field(s) are blank");
            return false;
        }
        else {
            UpdateResponse(response, 200, "User " + service.getUser(email) + " successfully retrieved");
            return true;
        }
    }


     */

    public User getUser(Request request, Response response) {
        //JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        String email = request.session().attribute("email");

        UpdateResponse(response, 200, "User " + service.getUser(email) + " successfully retrieved");
        return service.getUser(email).get();
    }


    public Boolean Register(Request request, Response response) {
        String name =  request.queryParams("name");
        String password =  request.queryParams("password");
        String phone =  request.queryParams("phone");
        String email =  request.queryParams("email");

        if(name.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            UpdateResponse(response,401, "Fields are blank");
            return false;
        }

        if(service.getUser(email).isPresent()) {
            UpdateResponse(response,401, "User with that email already exists");
            return false;
        }

        else {
            User user = service.createUser(name, password, phone, email);
            response.cookie("username", user.getName());
            request.session(true);
            request.session().attribute("id", user.getID());
            request.session().attribute("name", user.getName());
            request.session().attribute("email", user.getEmail());
            UpdateResponse(response, 200, "Account creation successful");
            return true;
        }
    }


    public Boolean Login(Request request, Response response) {
        if(request.session().attribute("id") != null) {
            UpdateResponse(response,401, "You are already logged in");
            return false;
        }


        String password = request.queryParams("password");
        String email = request.queryParams("email");

        if(password.isEmpty() || email.isEmpty()) {
            UpdateResponse(response,401, "Fields are blank");
            return false;
        }

        Optional<User> user = service.getUser(email);

        if(user.isEmpty()) {
            UpdateResponse(response,401, "User does not exist");
            return false;
        }

        if(!user.get().getPassword().equals(password)) {
            UpdateResponse(response,401, "Wrong password");
            return false;
        }

        else {
            //request.session().attribute("logged in", user );
            response.cookie("sessid", request.session().id());
            request.session().attribute("id", user.get().getID());
            request.session().attribute("name", user.get().getName());
            request.session().attribute("email", user.get().getEmail());
            //System.out.println(request.session().attribute("email").toString());
            UpdateResponse(response,200, "Login successful");
            return true;
        }

    }

    public Boolean Logout(Request request, Response response) {
        if(request.session().attribute("id") != null) {
            request.session().invalidate();
            UpdateResponse(response, 200, "Logout successful");
            return true;
        }
        else{
            UpdateResponse(response,401,"No user login is currently detected");
            return false;
        }
    }


    public boolean sendPost(Request request, Response response) {
        if(request.session().attribute("id") == null) {
            UpdateResponse(response,401, "You are not logged in");
            return false;
        }

        String email = request.session().attribute("email");
        String friendemail = request.queryParams("friendEmail");
        String posttext = request.queryParams("postText");

        if(email.isEmpty() || friendemail.isEmpty() || posttext.isEmpty()){
            UpdateResponse(response,401, "Fields are blank");
            return false;
        }

        Optional<User> user = service.getUser(email);
        Optional<User> friend = service .getUser(friendemail);

        if(user.isEmpty() || friend.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }

        else {
            Post post = service.sendPost(posttext, user.get(), friend.get());
            UpdateResponse(response, 200, post.getPostText());
            return true;
        }
    }

    public List<Post> getFeed(Request request, Response response){
        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        //   String email = requestObject.get("email").getAsString();
        String email = request.queryParams("email");
        // long id  = service.getUser(email).get().getID();
        return service.getFeed(service.getUser(email).get());
    }

        public Boolean GetFriendRequests(Request request, Response response) {
            JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
            String email = request.queryParams("email");
            //String email = request.session().attribute("email");
        /*
        if(email.isEmpty()){
            UpdateResponse(response,401, "Email field is blank");
            return new List<User>;
        }

         */

            Optional<User> user = service.getUser(email);

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

    public Boolean SendFriendRequest(Request request, Response response) {

        if(request.session().attribute("name") == null) {
            UpdateResponse(response,401, "You are not logged in");
            return false;
        }

        String email = request.session().attribute("email");
        String friendemail = request.queryParams("friendemail");

        if(email.isEmpty() || friendemail.isEmpty()){
            UpdateResponse(response,401, "Fields are blank");
            return false;
        }

        Optional<User> user = service.getUser(email);
        Optional<User> friend = service.getUser(friendemail);
        List<Long> friendPendingFriendRequests = service.getFriendRequests(friend.get());
        List<Long> userFriends = service.getFriends(user.get());

        if (user.isEmpty() || friend.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }
        if (email.equals(friendemail)){
            UpdateResponse(response,401, "Cannot send friend request to self");
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
/*
    public Boolean GetFriendRequests (Request request, Response response) {
        if(request.session().attribute("id") == null) {
            UpdateResponse(response,401, "You are not logged in");
            return false;
        }

        String email = request.session().attribute("email");
        Optional<User> user = service.getUser(email);

        if(email.isEmpty()){
            UpdateResponse(response,401, "Email field is blank");
            return false;
        }

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

 */

    public Boolean RespondtoFriendRequest(Request request, Response response) {
        if(request.session().attribute("id") == null) {
            UpdateResponse(response,401, "You are not logged in");
            return false;
        }

        String email = request.session().attribute("email");
        String friendrequestresponse = request.queryParams("friendrequestresponse");
        String friendemail = request.queryParams("friendemail");

        if (email.isEmpty()|| friendemail.isEmpty() || friendrequestresponse.isEmpty()){
            UpdateResponse(response,401, "Field(s) are blank");
            return false;
        }

        Optional<User> user = service.getUser(email);
        Optional<User> friend = service.getUser(friendemail);
        List<Long> userFriendRequests = service.getFriendRequests(user.get());

        if (!friendrequestresponse.equals("accept") && !friendrequestresponse.equals("reject")){
            UpdateResponse(response,401, "Invalid response to friend request: either accept or reject");
            return false;
        }

        if (user.isEmpty() || friend.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }

        if(userFriendRequests.contains(friend.get().getID()) && friendrequestresponse.equals("accept")) {
            service.acceptFriendRequest(user.get(),friend.get());
            UpdateResponse(response,401, "Friend request accepted");
            return true;
        }

        if(userFriendRequests.contains(friend.get().getID()) && friendrequestresponse.equals("reject")) {
            service.rejectFriendRequest(user.get(),friend.get());
            UpdateResponse(response,401, "Friend request rejected");
            return true;
        }

        else {
            UpdateResponse(response,401, "Failed to respond to friend request");
            return false;
        }
    }

    public Boolean GetFriends (Request request, Response response) {
        if(request.session().attribute("id") == null) {
            UpdateResponse(response,401, "You are not logged in");
            return false;
        }

        String email = request.session().attribute("email");
        if (email.isEmpty()){
            UpdateResponse(response,401, "Field(s) are blank");
            return false;
        }
        Optional<User> user = service.getUser(email);
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
        if(request.session().attribute("id") == null) {
            UpdateResponse(response,401, "You are not logged in");
            return false;
        }

        String email = request.session().attribute("email");
        String newName = request.queryParams("newName");
        String newPassword = request.queryParams("newPassword");
        String newPhone = request.queryParams("newPhone");

        if (email.isEmpty() || newName.isEmpty() || newPassword.isEmpty() || newPhone.isEmpty()){
            UpdateResponse(response,401, "Field(s) are blank");
            return false;
        }

        Optional<User> user =service.getUser(email);

        if(user.isEmpty()) {
            UpdateResponse(response,401, "User(s) don't exist");
            return false;
        }

        else {
            service.updateUser(
                    user.get(),
                    newName,
                    newPassword,
                    newPhone);
            UpdateResponse(response,200, "Account updated successfully for " + user.get().getName());
            return true;
        }
    }
}