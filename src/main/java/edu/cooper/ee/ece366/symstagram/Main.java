package edu.cooper.ee.ece366.symstagram;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.cooper.ee.ece366.symstagram.store.PlatformStoreImpl;
import edu.cooper.ee.ece366.symstagram.util.JsonTransformer;
import org.jdbi.v3.core.Jdbi;
import spark.Spark;

public class Main {
    public static void main(String[] args) throws SQLException {
        Gson gson = new Gson();
        String url = "jdbc:h2:tcp://localhost/~/test";
        Connection connection = DriverManager.getConnection(url, "test", "test");
        Jdbi jdbi = Jdbi.create(url, "test","test");
        PlatformStoreImpl platformStore = new PlatformStoreImpl(jdbi);
        platformStore.populateDb();
        Service service = new Service(platformStore);
        Handler handler = new Handler(service);

        JsonTransformer jsonTransformer = new JsonTransformer();

        Spark.get("/ping", (req, res) -> "OK");
        Spark.get("/users", (request, response) -> handler.GetUsers(request, response), jsonTransformer);

        //Create a user object (account)
        Spark.post("/register", (request, response) -> handler.Register(request, response), jsonTransformer);
        //Login with email and password
        Spark.post("/login", (request, response) -> handler.Login(request, response), jsonTransformer);
        //edit info of the profile
        Spark.put("/update", (request, response) -> handler.editInfo(request, response), jsonTransformer);

        //Send a friend request
        Spark.put("/sendfriendrequest", (request, response) -> handler.SendFriendRequest(request, response), jsonTransformer);

        //Get all pending friend requests of a user object
        Spark.get("/getfriendrequests", (request, response) -> handler.GetFriendRequests(request, response), jsonTransformer);

        //Accept a single friend request
        Spark.put("/acceptfriendrequest", (request, response) -> handler.AcceptFriendRequest(request, response), jsonTransformer);

        //Get all friends of a user object
        Spark.get("/friends", (request, response) -> handler.GetFriends(request, response), jsonTransformer);

        Spark.post("/sendPost", (request, response) -> handler.sendPost(request, response), jsonTransformer);

    }
}
