package s2

import org.scalatest.FunSuite

class SolutionTest extends FunSuite {
  test("path1") {
    assert(Solution.solution(Array(1,0,0,1,1)) === Array(1,1,0,1))
  }

  test("path2") {
    assert(Solution.solution(Array(1,0,0,1,1,1)) === Array(1,1,0,1,0,1,1))
  }

  test("intFrom1") {
    assert(Solution.intFrom(Array(1,0,0,1,1)) === 9)
  }
}
