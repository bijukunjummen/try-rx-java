package obs.basic

import java.util.concurrent.CountDownLatch

import org.scalatest.FunSuite
import rx.lang.scala.{Subscription, Observer, Observable}

import rx.lang.scala.JavaConversions._
import scala.concurrent.duration.DurationInt

import rx.{Observable=>jObservable}

class BasicObsTest extends FunSuite {
  test("A basic observable flow") {
    val service = new AService
    service.operation().subscribe(s => println(s))
  }

  test("Another basic with interval") {
    val o = Observable.interval(1 second)
    val latch = new CountDownLatch(1)
    o.subscribe(i => {
      print(i)
      if (i >= 5) latch.countDown()

    })
    latch.await()
  }
  
}

class AService {
  def operation(): Observable[String] = {
    Observable.create[String](o => {
      o.onNext("Operation 1")
      o.onCompleted()
      Subscription{}
    })
  }
}