package iterables

import org.scalatest.FunSuite
import rx.lang.scala.Observable

class BasicIterableTest extends FunSuite {
  test("a basic map getData on list") {
    val l = List(1, 2, 3, 4)
    val l1 = l flatMap { i => List(i, i * i) }
    assert(l1 === List(1, 1, 2, 4, 3, 9, 4, 16))
  }
  
}