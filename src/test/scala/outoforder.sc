import rx.lang.scala.{Subscriber, Observable}
import scala.concurrent.duration._

object outoforder {

  val o = Observable.just(1, 2, 3, 4, 100, 200)

  o.filter(i => i > 10).toBlocking.foreach(i => println(i))

  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}