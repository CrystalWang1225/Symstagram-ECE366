##Log in and run requests like usual

#Create account for Crystal
curl localhost:4567/register -X post --data "name=Crystal&password=password123&phone=1112223333&email=wang@cooper.edu"
read junk

#Create account for Brian
curl localhost:4567/register -X post --data "name=Brian&password=password123&phone=1112223333&email=chung3@cooper.edu"
read junk

#Login into Crystal's account
curl localhost:4567/login -X post --data "email=wang@cooper.edu&password=abc123" -c cookies.txt
read junk

#Update Crystal's account
#curl localhost:4567/update -X put --data "newName=Krystal&newPassword=abc123&newPhone=1234565432" -b cookies.txt
#read junk

#Send a friend request from Crystal to Brian
curl localhost:4567/sendfriendrequest -X put --data "friendemail=chung3@cooper.edu" -b cookies.txt
read junk

#Log out of Crystal's account
curl localhost:4567/logout -X get -b cookies.txt
read junk

#Log into Brian's account
curl localhost:4567/login -X post --data "email=chung3@cooper.edu&password=password123" -c cookies.txt
read junk

#See Brian's pending friend requests
curl localhost:4567/getfriendrequests -b cookies.txt
read junk

#Accept Crystal's friend request
curl localhost:4567/respondtofriendrequest -X put --data "friendemail=wang@cooper.edu&friendrequestresponse=accept" -b cookies.txt
read junk

#Get all friends of Brian
curl localhost:4567/friends -b cookies.txt
read junk

#Log out of Brian's account
curl localhost:4567/logout -X get -b cookies.txt
read junk

#Attempting requests without being logged in
curl localhost:4567/getfriendrequests -b cookies.txt
read junk
curl localhost:4567/sendfriendrequest -X put --data "friendemail=chung3@cooper.edu" -b cookies.txt
read junk
curl localhost:4567/respondtofriendrequest -X put --data "friendemail=wang@cooper.edu&friendrequestresponse=accept" -b cookies.txt
read junk