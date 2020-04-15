Brian Chung
Friend Requests subsystem deliverable

NEW DELIVERABLES:

I developed three new main functions to introduce Friend Requests to our system:
1) Send a friend request from one user object to another user object
CODE: Spark.put("/sendfriendrequest", (request, response) -> handler.SendFriendRequest(request, response), jsonTransformer);
2) See a user object's pending friend requests
CODE: Spark.get("/getfriendrequests", (request, response) -> handler.GetFriendRequests(request, response), jsonTransformer);
3) Accept a pending friend request by specifying which user object the friend request came from
CODE: Spark.put("/acceptfriendrequest", (request, response) -> handler.AcceptFriendRequest(request, response), jsonTransformer);
*The commands to test these main functions are in the test.sh script file

I also created two main functions to better debug and monitor the system:
1) List all users currently registered in the system
CODE: Spark.post("/register", (request, response) -> handler.Register(request, response), jsonTransformer);
2) List all friends of a specified user
CODE: Spark.get("/friends", (request, response) -> handler.GetFriends(request, response), jsonTransformer);

To store friend requests and friends, I added two ArrayLists<Users> objects to the User class. One is for friend requests and the other is for friends.
In order to know where to send and receive requests, I used the User class variable "String email", since it is the key for the userSet hashmap (the hashmap where all user objects are stored). That is why the email field is used in the command line tests.

HOW TO RUN:

First create two test user accounts with the commands below.
Create Brian's user account: curl localhost:4567/register -X post --data "name=Brian&password=password123&phone=9084583054&email=chung3@cooper.edu"
Create Manny's user account: curl localhost:4567/register -X post --data "name=Manny&password=password123&phone=1112223333&email=manny@cooper.edu"
Outputs will be String outputs listing the user object's local variable values, looks like this: {"name":"Brian","phone":"9084583054","email":"chung3@cooper.edu"}

Next, check if users are registered in the system.
Retrieve all users: curl localhost:4567/users

Then send a friend request from Manny's user account to Brian's user account. Notice the current user is specified with the parameter "email", while the friend is specified with "friendemail"
Send request: curl localhost:4567/sendfriendrequest -X put --data "email=manny@cooper.edu&friendemail=chung3@cooper.edu"

Now check Brian's pending friend requests: curl localhost:4567/getfriendrequests?email=chung3@cooper.edu

Test if Brian can successfully accept Manny's pending friend request.
Accept request: curl localhost:4567/acceptfriendrequest -X put --data "email=chung3@cooper.edu&friendemail=manny@cooper.edu"

Finally, list all of Brian's friends.
Retrieve all of Brian's friends: curl localhost:4567/friends?email=chung3@cooper.edu