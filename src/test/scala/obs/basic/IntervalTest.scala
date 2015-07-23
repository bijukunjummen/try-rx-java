package obs.basic

import rx.lang.scala.{ Subscriber, Observable }
import scala.concurrent.duration._

import org.scalatest.FunSuite

class IntervalTest extends FunSuite {
  test("An interval of 1 second") {
    val ticks: Observable[Long] = Observable.interval(1 second)
    val evens = ticks.filter(_ % 2 == 0)
    val bufs = evens.slidingBuffer(count = 2, skip = 1)
    val s = bufs.subscribe(println(_))
    
    Thread.sleep(15000l)
  }
}