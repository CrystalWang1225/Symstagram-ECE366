
Sessions subsystem deliverable

NEW DELIVERABLES:
I added new changes as described below: 
1) Basic implementation of sessions: you can log in and log out. Requests will be accepted or denied depending on login status

2) Swapping out request.queryParams() for request.attribute() whenever appropriate to reduce redundant information input

3) Turning Handler into a package so there is a backend version and a full system version of handler. right now I cannot test the json object unwrapping so I just used request.queryParams() method of input instead

To demo sessions, just run the session_demo.sh test script

