package sol1;// you can also use imports, for example:
// import java.util.*;

// you can use System.out.println for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int X, int[] A) {
        int len = A.length;
        if (len == 0 ) return 0;

        int countBefore = 0;
        int countAfter = getNegCount(X, A, 0, A.length - 1);

        for (int i = 0; i <= A.length - 1; i++) {

            if (A[i] == X) {
                countBefore++;
            } else {
                countAfter--;
            }
            if (countBefore == countAfter) {
                return i + 1;
            }

        }
        return 0;
    }

    private int getNegCount(int X, int[] A, int lo, int hi) {
        int count = 0;
        for (int i=lo;i <= hi; i++) {
            if (A[i] != X) {
                count++;
            }
        }
        return count;
    }
}