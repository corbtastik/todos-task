# Todos Spring Cloud Task example

Howdy!  This is a small [Spring Cloud Task](https://cloud.spring.io/spring-cloud-task/) example that simply triggers a Stream defined as (Source | Processor | Sink).  

## Use

### Clone 3 Stream apps

* [Todos Task](https://github.com/corbtastik/todos-task) - trigger a fake Todo and send to Stream
* [Todos Source](https://github.com/corbtastik/todos-source) - event Todos
* [Todos Processor](https://github.com/corbtastik/todos-processor) - process Todos
* [Todos Sink](https://github.com/corbtastik/todos-sink) - handle Todos

### Build each app

``mvnw clean package``

### Start each App and lastly start Task

```bash
java -jar ./target/todos-source-1.0.0-SNAP.jar --server.port=8080
java -jar ./target/todos-processor-1.0.0-SNAP.jar --server.port=8081
java -jar ./target/todos-sink-1.0.0-SNAP.jar --server.port=8082
java -jar ./target/todos-task-1.0.0-SNAP.jar --server.port=8083 --todos.api.endpoint=http://localhost:8080
```

### Observe

If a Todo has a hashtag the Processor adds it into a Set and republishes the event which in-turn is handled by the Sink and added into a Hashtag Index.

You can see evidence of this by tailing the log of the Sink while you Source a Todo with a hashtag in the title.
