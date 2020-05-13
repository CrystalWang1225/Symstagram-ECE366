
package edu.cooper.ee.ece366.symstagram.handler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.cooper.ee.ece366.symstagram.Service;
import edu.cooper.ee.ece366.symstagram.model.Post;
import edu.cooper.ee.ece366.symstagram.model.User;
import spark.Request;
import spark.Response;

import java.util.*;

public class HandlerFullSystemImpl implements Handler {
    private final Service service;

    HashMap<String, User> userSet = new HashMap<String, User>();

    public HandlerFullSystemImpl(Service service) {
        this.service = service;
    }
    //  HashMap<String,Post> postSet = new HashMap<String, Post>();


    static void UpdateResponse(Response response, Integer code, String message) {
        response.status(code);
        response.body(message);
        System.out.println(message);
    }


    @Override
    public Boolean Register(Request request, Response response) {
        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        String name = requestObject.get("name").getAsString();
        String password = requestObject.get("password").getAsString();
        String phone = requestObject.get("phone").getAsString();
        String email =  requestObject.get("email").getAsString();

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

    @Override
    public Boolean Login(Request request, Response response) {

        /*
        if(request.session().attribute("id") != null) {
            UpdateResponse(response,401, "You are already logged in");
            return false;
        }

         */

        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        String email = requestObject.get("email").getAsString();
        String password = requestObject.get("password").getAsString();

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
            request.session().attribute("logged in", user );
            response.cookie("sessid", request.session().id());
            request.session().attribute("id", user.get().getID());
            request.session().attribute("name", user.get().getName());
            request.session().attribute("email", user.get().getEmail());
            UpdateResponse(response,200, "Login successful");
            return true;
        }

    }

    @Override
    public Boolean Logout(Request request, Response response){
        if(request.session().attribute("logged in") != null) {
            request.session().removeAttribute("logged in");
            UpdateResponse(response, 200, "Logout successful");
            return true;
        }
        else{
            UpdateResponse(response,401,"No user login is currently detected");
            return false;
        }
    }

    @Override
    public boolean sendPost(Request request, Response response){

        /*
        if(request.session().attribute("id") == null) {
            UpdateResponse(response,401, "You are not logged in");
            return false;
        }

         */

        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
       // String email = request.session().attribute("email");
        String email = requestObject.get("email").getAsString();
        String friendemail = requestObject.get("friendemail").getAsString();
        String posttext = requestObject.get("posttext").getAsString();

        if(email.isEmpty() || friendemail.isEmpty() || posttext.isEmpty()) {
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



    @Override
    public Boolean SendFriendRequest(Request request, Response response) {
        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        String email = requestObject.get("email").getAsString();
        String friendemail = requestObject.get("friendemail").getAsString();
      //  String email = request.queryParams("email");
        //String friendemail = request.queryParams("friendemail");

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

    @Override
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
            return  true;
        }
    }

    @Override
    public Boolean RespondtoFriendRequest(Request request, Response response) {
        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        String email = request.session().attribute("email");
        String friendrequestresponse = requestObject.get("friendemail").getAsString();
        String friendemail = requestObject.get("friendemail").getAsString();

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

    @Override
    public Boolean GetFriends(Request request, Response response) {
        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        String email = requestObject.get("email").getAsString();
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

//    public Boolean GetCurrentUser(Request request, Response response) {
//
//    };

    public User getUser(Request request, Response response){
        //JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        String email = request.session().attribute("email");
        UpdateResponse(response, 200, "User " + service.getUser(email) + " successfully retrieved");
        return service.getUser(email).get();

    }

//    public Optional<User> getUser1(Request request, Response response){
//           JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
//           String email = requestObject.get("email").getAsString();
//           return service.getUser(email);
//       }

    @Override
    public Boolean editInfo(Request request, Response response){
        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
        String email = requestObject.get("email").getAsString();
        String newName = requestObject.get("newName").getAsString();
        String newPassword = requestObject.get("newPassword").getAsString();
        String newPhone = requestObject.get("newPhone").getAsString();

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


    public List<Post> getFeed(Request request, Response response){
        JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
     //   String email = requestObject.get("email").getAsString();
        String email = request.queryParams("email");
       // long id  = service.getUser(email).get().getID();
        UpdateResponse(response,200, "get feed successfullly" +service.getUser(email).get() );
        return service.getFeed(service.getUser(email).get());
    }


}
