package s2

import scala.collection.immutable.Queue

object Solution {
  def solution(arr: Array[Int]): Array[Int] = {
    val arrVal = intFrom(arr)
    val tbd = -1 * arrVal;
    checkMatches(tbd)
  }

  def intFrom(arr: Iterable[Int]) = arr.zipWithIndex.foldLeft(0)((sum, t) => t match {case (e, i) => sum + e * math.pow(-2, i).toInt})

  def checkMatches(n: Int): Array[Int] = {
    val l = checkPaths(n, Queue(0 :: Nil, 1 :: Nil))
    l.toArray
  }

  def checkPaths(n: Int, queue: Queue[List[Int]]): List[Int] = {
    queue.dequeue match {
      case (l, tail) => {
        if (intFrom(l) == n) {
          l
        } else {
          checkPaths(n, tail :+ (1 :: l) :+ (0 :: l))
        }
      }
    }
  }
}
