curl localhost:4567/login -X post --data "email=chung3@cooper.edu&password=password123"
read junk

#returned "login successful"
curl localhost:4567/login -X post --data "email=&password=password123"
read junk
