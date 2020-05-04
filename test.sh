#Create a user with the parameters listed below
curl localhost:4567/register -X post --data "name=Brian&password=password123&phone=9084583054&email=chung3@cooper.edu"

#Create another user with the parameters listed below
curl localhost:4567/register -X post --data "name=Manny&password=password123&phone=1112223333&email=manny@cooper.edu"

#List all users
curl localhost:4567/users

#Send a friend request from user with parameter "email" to a friend account with parameter "friendemail"
curl localhost:4567/sendfriendrequest -X put --data "email=manny@cooper.edu&friendemail=chung3@cooper.edu"

#Get all pending friend requests
curl localhost:4567/getfriendrequests?email=chung3@cooper.edu

#Accept a single friend request from a friend user specified by "friendemail"
curl localhost:4567/acceptfriendrequest -X put --data "email=chung3@cooper.edu&friendemail=manny@cooper.edu"

#Get all friends of the user with "email"
curl localhost:4567/friends?email=chung3@cooper.edu

#Send a post to a friend
curl localhost:4567/sendPost -X post --data "email=chung3@cooper.edu&friendEmail=manny@cooper.edu&postText=justalittletest"