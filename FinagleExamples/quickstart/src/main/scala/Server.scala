import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}

/*
To run:
$ ./sbt 'runMain Server'
Which exposes an HTTP server on port 8080 which dispatches requests to service

Test with:
$ curl -D - localhost:8080
HTTP/1.1 200 OK
 */

object Server extends App {
  /**
   * Services are functions from a request type (com.twitter.finagle.http.Request)
   * to a Future of a response type (com.twitter.finagle.http.Response).
   * Put another way: given a request, we must promise a response some time in the future.
   * In this case, we just return a trivial HTTP-200 response immediately
   * (through Future.value), using the same version of HTTP with which the request was
   * dispatched.
   */
  val service = new Service[http.Request, http.Response] {
    def apply(req: http.Request): Future[http.Response] =
      Future.value(
        http.Response(req.version, http.Status.Ok)
      )
  }

  /**
   * The serve method takes two parameters:
   * @param addr: SocketAddress -> a bind target (which port to expose the server)
   * @param service: service: Service[Req, Rep] -> the service itself.
   * @returns ListeningServer
   *
   * The server is responsible for listening for incoming connections, translating the HTTP wire
   * protocol into com.twitter.finagle.http.Request objects, and translating our
   * com.twitter.finagle.http.Response object back into its wire format, sending replies back to
   * the client.
   */
  val server = Http.serve(":8080", service)
  Await.ready(server)
}
