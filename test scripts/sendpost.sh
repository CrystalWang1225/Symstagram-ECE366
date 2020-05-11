#Works! Responds user doesnt exist
curl localhost:4567/sendPost -X post --data "email=chung3@cooper.edu&friendEmail=manny@cooper.edu&postText=justalittletest"

#Works! Responds the receiver is not your friend
curl localhost:4567/sendPost -X post --data "email=chung3@cooper.edu&friendEmail=hong@cooper.edu&postText=justalittletest"

#Works! Responds fields are blank
curl localhost:4567/sendPost -X post --data "email=chung3@cooper.edu&friendEmail=chris@cooper.edu&postText="

