run server
```
make run
```

`twitter_account.id` is the internal primary key for the twitter account you'd like to access.


To get your home timeline feed:
```
curl http://localhost:8080/twitter/accounts/{twitter_account.id}/home-timeline
```

To post a status
```
curl -X POST \
  http://localhost:8080/twitter/accounts/twitter_account.id}/tweet \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{"text":"<your message here>"}'
```

TODO
[ ] get exception mapping working (bug causing ""There was an error processing your request...")
