import com.twitter.util.{Await, Future, FuturePool}

/**
 * When you have work that is blocking, say I/O or a library not written in an asynchronous style, you should use a
 * com.twitter.util.FuturePool. FuturePool manages a pool of threads that don’t do any other work, which means that
 * blocking operations won’t halt other asynchronous work.
 *
 * In the code below, someIO is an operation that waits for I/O and returns a string (e.g., reading from a file).
 * Wrapping someIO(): String in FuturePool.unboundedPool returns a Future[String], which allows us to combine this
 * blocking operation with other Futures in a safe way.
 */

object Blocking extends App {

  // does some blocking I/O and returns a string
  def someIO(): String = System.console().readLine()

  def futureResult(): Future[String] = FuturePool.unboundedPool {
    someIO()
  }

  val string1 = someIO()
  println(s"Blocks!!!!! got $string1")

  val string2 = futureResult()
  println("Non Blocking!")

  val string3 =Await.result(string2)
  println(s"got it: $string3")

}
