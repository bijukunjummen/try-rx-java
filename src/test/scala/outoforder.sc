import rx.lang.scala.{Subscriber, Observable}
import scala.concurrent.duration._

object outoforder {

	val ticks: Observable[Long] = Observable.interval(1 second)
                                                  //> ticks  : rx.lang.scala.Observable[Long] = rx.lang.scala.JavaConversions$$ano
                                                  //| n$2@5b87ed94
	val evens = ticks.filter(_ % 2 == 0)      //> evens  : rx.lang.scala.Observable[Long] = rx.lang.scala.JavaConversions$$ano
                                                  //| n$2@3b084709
 	val bufs = evens.slidingBuffer(count = 2, skip = 1)
                                                  //> bufs  : rx.lang.scala.Observable[Seq[Long]] = rx.lang.scala.JavaConversions$
                                                  //| $anon$2@7c29daf3
 	
 	val s = bufs.subscribe(println(_))        //> s  : rx.lang.scala.Subscription = rx.lang.scala.Subscription$$anon$2@4b1c1ea
                                                  //| 0
	
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
}