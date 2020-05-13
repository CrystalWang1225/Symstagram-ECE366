package edu.cooper.ee.ece366.symstagram;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.cooper.ee.ece366.symstagram.handler.HandlerFullSystemImpl;
import edu.cooper.ee.ece366.symstagram.store.PlatformStoreImpl;
import edu.cooper.ee.ece366.symstagram.util.JsonTransformer;
import org.jdbi.v3.core.Jdbi;
import spark.Spark;

public class Main {
    public static void main(String[] args) throws SQLException {
        Spark.staticFiles.location("/public");

        Gson gson = new Gson();
        String url = "jdbc:mysql://localhost:3307/symstagram?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST";
        Connection connection = DriverManager.getConnection(url, "test", "test");
        Jdbi jdbi = Jdbi.create(url, "test","test");
        PlatformStoreImpl platformStore = new PlatformStoreImpl(jdbi);
        platformStore.populateDb();
        Service service = new Service(platformStore);

        HandlerFullSystemImpl handler = new HandlerFullSystemImpl(service);
        JsonTransformer jsonTransformer = new JsonTransformer();

        Spark.staticFiles.header("Access-Control-Allow-Origin", "http://localhost:3000");

        Spark.get("/ping", (req, res) -> "OK");

        //Create a user object (account)
        Spark.post("/register", (request, response) -> handler.Register(request, response), jsonTransformer);
        //Login with email and password
        Spark.post("/login", (request, response) -> handler.Login(request, response), jsonTransformer);
        //edit info of the profile
        Spark.put("/update", (request, response) -> handler.editInfo(request, response), jsonTransformer);
        //Logout from a session
        Spark.get("/logout", (request, response) -> handler.Logout(request, response), jsonTransformer);
        //Send a friend request
        Spark.get("/getuser",(request, response) -> handler.getUser(request, response), jsonTransformer );

        Spark.put("/sendfriendrequest", (request, response) -> handler.SendFriendRequest(request, response), jsonTransformer);

        //Get all pending friend requests of a user object
        Spark.get("/getfriendrequests", (request, response) -> handler.GetFriendRequests(request, response), jsonTransformer);

        //Accept a single friend request
      //  Spark.put("/acceptfriendrequest", (request, response) -> handler.AcceptFriendRequest(request, response), jsonTransformer);

        //Get all friends of a user object
        Spark.get("/friends", (request, response) -> handler.GetFriends(request, response), jsonTransformer);

        //Accept a single friend request
        Spark.put("/respondtofriendrequest", (request, response) -> handler.RespondtoFriendRequest(request, response), jsonTransformer);
        Spark.post("/sendPost", (request, response) -> handler.sendPost(request, response), jsonTransformer);

        Spark.get("/getfeed", (request, response) -> handler.getFeed(request, response), jsonTransformer);

        Spark.options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        Spark.before((request, response) ->  {
            response.header("Access-Control-Allow-Origin", "http://localhost:3000");
            response.header("Access-Control-Allow-Credentials", "true");
        });


    }
}
