import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}

/**
 * run with
 * $ ./sbt 'runMain Client'
 */

object Client extends App {
  /**
   * In our server example, we define a Service to respond to requests.
   * Clients work the other way around: we’re given a Service to use.
   *
   * Just as we exported services with the Http.serve, method, we can
   * import them with a Http.newService, giving us an instance of
   * Service[http.Request, http.Response]:
   */
  val client: Service[http.Request, http.Response] = Http.client
    .withTransport.tls("scala-lang.org")
    .newService("scala-lang.org:443")


  /**
   * client is a Service to which we can dispatch an http.Request and in
   * return receive a Future[http.Response] — the promise of an http.Response
   * (or an error) some time in the future. We furnish newService with the target
   * of the client: the host or set of hosts to which requests are dispatched.
   */
  val request = http.Request(http.Method.Get, "/")
  request.host = "www.scala-lang.org"
  val response: Future[http.Response] = client(request)

  /**
   * Now that we have response, a Future[http.Response], we can register a
   * callback to notify us when the result is ready:
   */
  Await.result(response.onSuccess { rep: http.Response => println("GET success: " + rep) })
}
