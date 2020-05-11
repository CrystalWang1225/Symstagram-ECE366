#Works! "login successful"
curl localhost:4567/login -X post --data "email=chung3@cooper.edu&password=password123"
read junk

#Works! "fields are blank"
curl localhost:4567/login -X post --data "email=&password=password123"
read junk

#Works! "fields are blank"
curl localhost:4567/login -X post --data "email=chung3@cooper.edu&password="
read junk

#Works! "User does not exist"
curl localhost:4567/login -X post --data "email=wang@cooper.edu&password=password123"
read junk

#Before Login: Works! "No user login is currently detected"
curl localhost:4567/logout

#After login: Error! [qtp907343886-22] INFO spark.http.matching.MatcherFilter - The requested route [/log/logout] has not been mapped in Spark for Accept: [*/*]
curl localhost:4567/logout

