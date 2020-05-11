#Works! "Account creation successful" response on 1st try and "User with that email already exists" response on 2nd try
curl localhost:4567/register -X post --data "name=Brian&password=password123&phone=9084583054&email=chung3@cooper.edu"

#Works! "Fields are blank" response
curl localhost:4567/register -X post --data "name=&password=password123&phone=9084583054&email=chung3@cooper.edu"

#Works! "Fields are blank" response
curl localhost:4567/register -X post --data "name=Brian&password=&phone=9084583054&email=hong@cooper.edu"

#Works! "Fields are blank" response
curl localhost:4567/register -X post --data "name=Brian&password=&phone=9084583054&email=hong@cooper.edu"

#Works! "Fields are blank" response
curl localhost:4567/update -X put --data "email=chung3@cooper.edu&newName=&newPassword=&newPhone="

#Works! "Account updated successfully for Brian" response
curl localhost:4567/update -X put --data "email=chung3@cooper.edu&newName=Brian&newPassword=abc123&newPhone=1234565432"

