package obs

import org.scalatest.FunSuite
import rx.lang.scala.Observable
import scala.concurrent.duration._

class BasicObsTest extends FunSuite {
  
  test("Obs with 1 second delay") {
    val ticks: Observable[Long] = Observable.interval(1 seconds)
    val evens: Observable[Long] = ticks.filter(_% 2 == 0)
    
    evens.take(10).foreach { x => println(x) }
  }
  
  test("out of order") {
    val xs = Observable.just(3, 2, 1)
    val yss = xs.flatMap(x => Observable.interval(x seconds).take(2))
    yss.foreach(println)
  }

}