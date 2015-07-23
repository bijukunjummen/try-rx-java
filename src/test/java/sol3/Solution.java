package sol3;
// you can also use imports, for example:
// import java.util.*;

// you can use System.out.println for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.List;

class Solution {
    public int solution(int[] A) {

        return 0;
    }

    public boolean crossPreviousSegments(Segment curr, List<Segment> prevs) {
        for (Segment seg: prevs) {
            if (seg.touches(curr)) {
                return true;
            }
        }
        return false;
    }

    static class Point {
        int x;
        int y;
    }

    static class Segment {
        Point a;
        Point b;
        SegmentType type;

        boolean touches(Segment seg) {

            //if in same direction they do not intersect..
//            if (seg.type == type) {
//                return false;
//            } else {
//                if (type == SegmentType.VERT) {
//                    return ((a.y < seg.a.y && b.y > seg.a.y) || (b.y < seg.a.y &&  )
//                }
//            }
            return false;
        }
    }

    enum SegmentType {
        VERT, HOR;
    }
}