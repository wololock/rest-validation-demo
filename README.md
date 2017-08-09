Request:

```
curl "localhost:8080/history?emp=AZ000001A"
```

Response:

```
{"id":"AZ000001A","name":"Joe Doe"}
```

Request:

```
curl "localhost:8080/history?emp=sd"
```

Response:

```
{"message":"Your request contains errors","errors":[{"getHistory.arg0":"Invalid emp"}]}
```
