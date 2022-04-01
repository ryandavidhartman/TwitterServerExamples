import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await

/**
 * Now we’re ready to create an HTTP proxy! Notice the symmetry above:
 * servers provide a Service, while a client uses it.
 * Indeed, an HTTP proxy can be constructed by just replacing the service
 * we defined in the Server example with one that was imported with a Http.newService:
 */

object Proxy extends App {
  val client: Service[Request, Response] = Http.client
    .withTransport.tls("twitter.com")
    .newService("twitter.com:443")

  val server = Http.serve(":8080", client)
  Await.ready(server)
}

