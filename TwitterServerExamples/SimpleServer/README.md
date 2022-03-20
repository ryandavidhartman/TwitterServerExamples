# SimpleServer

The most basic example of the Twitter Server.

## Code

```scala
object SimpleServer extends TwitterServer {

  val service = new Service[Request, Response] {
    def apply(request: Request): Future[Response] = {
      val response = Response(request.version, Status.Ok)
      response.contentString = "Hello"
      Future.value(response)
    }
  }

  def main(): Unit = {
    val server = Http.serve(":8888", service)
    onExit {
      server.close()
    }
    Await.ready(server)
  }

}
```

## To Run the Example
```shell
sbt assembly
java -jar ./target/scala-2.13/SimpleServer-assembly-0.1.jar &
 ```

This is will build the server and run it on port 8888

```shell
curl localhost:8888
```

To receive the response _Hello_
