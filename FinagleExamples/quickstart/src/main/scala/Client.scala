import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}

object Client extends App {
  //#builder
  val client: Service[http.Request, http.Response] = Http.client
    .withTransport.tls("scala-lang.org")
    .newService("scala-lang.org:443")
  //#builder
  //#dispatch
  val request = http.Request(http.Method.Get, "/")
  request.host = "www.scala-lang.org"
  val response: Future[http.Response] = client(request)
  //#dispatch
  //#callback
  Await.result(response.onSuccess { rep: http.Response => println("GET success: " + rep) })

  //#callback
}
