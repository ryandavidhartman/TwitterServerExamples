import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

class ExampleController extends Controller {

  get("/hi") { request: Request =>
    info("hi")
    "Hello " + request.params.getOrElse("name", "unnamed")
  }

  post("/hi") { request: ExampleRequest =>
    "Hello " + request.name + " with id " + request.id
  }
}