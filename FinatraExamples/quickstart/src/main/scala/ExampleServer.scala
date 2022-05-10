
import com.twitter.finagle.http.Request
import com.twitter.finagle.http.Response
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.AccessLoggingFilter
import com.twitter.finatra.http.filters.ExceptionMappingFilter
import com.twitter.finatra.http.filters.HttpNackFilter
import com.twitter.finatra.http.filters.HttpResponseFilter
import com.twitter.finatra.http.filters.LoggingMDCFilter
import com.twitter.finatra.http.filters.StatsFilter
import com.twitter.finatra.http.filters.TraceIdMDCFilter
import com.twitter.finatra.http.routing.HttpRouter

object ExampleServerServerMain extends ExampleServer

class ExampleServer extends HttpServer {

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[StatsFilter[Request]]
      .filter[AccessLoggingFilter[Request]]
      .filter[HttpResponseFilter[Request]]
      .filter[ExceptionMappingFilter[Request]]
      .filter[HttpNackFilter[Request]]
      .add[ExampleController]
  }
}
