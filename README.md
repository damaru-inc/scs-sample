# Sample Spring Cloud Stream app with parameterized topics.

To test it, run it and REST POST any string to localhost:8080

e.g.

```curl -H "Content-Type: text/plain" -X POST -d "Created an Order." http://localhost:8080```

Then try POST and DELETE to localhost:8080/123

```curl -H "Content-Type: text/plain" -X POST -d "Updated an Order." http://localhost:8080/12```

```curl -H "Content-Type: text/plain" -X DELETE -d "Cancelled an Order." http://localhost:8080/12```


