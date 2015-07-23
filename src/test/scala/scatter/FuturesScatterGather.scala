package scatter

import java.util.concurrent.CountDownLatch

import obs.Util
import org.scalatest.FunSuite

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Try, Failure, Success}
import scala.concurrent.duration._


class FuturesScatterGather extends FunSuite {

  test("Scatter Gather with Futures") {
    val cl = new CountDownLatch(1)
    val l = Future.sequence((1 to 10).map(generateTask(_)).map(futureToFutureTry(_)))


    l.onComplete {
      case Success(v) => println(v)
      case Failure(t) => println(t)  
    }

    Await.result(l, 10 seconds);

  }

  def futureToFutureTry[T](f: Future[T]): Future[Try[T]] =
    f.map(Success(_)).recover({ case e => Failure(e)})

  def generateTask(i: Int):Future[String] = Future {
    Util.delay(2000)
    if (i == 5) {
      throw new RuntimeException("Oh it is 5!")
    }
    i + "-test"
  }

}
