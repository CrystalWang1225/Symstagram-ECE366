package edu.cooper.ee.ece366.symstagram.handler;

import edu.cooper.ee.ece366.symstagram.model.Post;
import edu.cooper.ee.ece366.symstagram.model.User;
import org.checkerframework.checker.nullness.Opt;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Optional;

public interface Handler {

    Boolean Register(Request request, Response response);

    boolean sendPost(Request request, Response response);

    Boolean Login(Request request, Response response);

    Boolean Logout(Request request, Response response);

    Boolean SendFriendRequest(Request request, Response response);

    Boolean GetFriendRequests(Request request, Response response);

    Boolean RespondtoFriendRequest(Request request, Response response);

    Boolean GetFriends(Request request, Response response);

    User getUser(Request request, Response response);

    List<Post> getFeed(Request request, Response response);
    //Optional<User> getUser(Request request, Response response);

       // Optional<User> getUser(Request request, Response response);

    /*   public Boolean getUser(Request request, Response response){
               JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
               String email = requestObject.get("email").getAsString();
               if (email.isEmpty()){
                   UpdateResponse(response,401, "Field(s) are blank");
                   return false;
               }
               else {
                   UpdateResponse(response, 200, "User " + service.getUser(email) + " successfully retrieved");
                   return true;
               }
           }
           public Optional<User> getUser1(Request request, Response response){
               JsonObject requestObject = new Gson().fromJson(request.body(), JsonObject.class);
               String email = requestObject.get("email").getAsString();
               return service.getUser(email);
           }
       */
    Boolean editInfo(Request request, Response response);
}