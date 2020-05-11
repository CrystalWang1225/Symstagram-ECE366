#Works! "Cannot send friend request to self"
curl localhost:4567/sendfriendrequest -X put --data "email=chung3@cooper.edu&friendemail=chung3@cooper.edu"

#Error! Responds Internal Server Error instead of "User(s) don't exist"
#java.util.NoSuchElementException: No value present
curl localhost:4567/sendfriendrequest -X put --data "email=chung3@cooper.edu&friendemail=crystal@cooper.edu"

#Works! Responds friend request sent first time. after accepted, responds already friends
curl localhost:4567/sendfriendrequest -X put --data "email=chung3@cooper.edu&friendemail=hong7@cooper.edu"
#Works! Responds "friend request failed to send" since friend user already sent the user a friend request
curl localhost:4567/sendfriendrequest -X put --data "email=hong7@cooper.edu&friendemail=chung3@cooper.edu"
#Works! Responds fields are blank
curl localhost:4567/sendfriendrequest -X put --data "email=chris@cooper.edu&friendemail="

#Works! List of pending friend requests successfully retrieved
curl localhost:4567/getfriendrequests?email=chung3@cooper.edu

#Works! User(s) don't exist
curl localhost:4567/getfriendrequests?email=crystal@cooper.edu

#Works! Email field is blank
curl localhost:4567/getfriendrequests?email=crystal@cooper.edu

#Error! <html><body><h2>404 Not found</h2></body></html>  [qtp1811484068-23] INFO spark.http.matching.MatcherFilter - The requested route [/RespondtoFriendRequest] has not been mapped in Spark for Accept: [*/*]
curl localhost:4567/RespondtoFriendRequest -X put --data "email=chris@cooper.edu&friendrequestresponse=accept&friendemail=chung3@cooper.edu"


