package sol2;// you can also use imports, for example:
// import java.util.*;

// you can use System.out.println for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.*;

class Solution {
    public int[] solution(int[] A) {
        long num = value(A);
        long tbd = num * -1;
        List<Integer> matched = checkMatches(tbd);
        return listToArray(matched);
    }

    public long value(int[] A) {
        long sum = 0;
        for (int i=0;i < A.length; i++) {
            int l = A[i];
            if (l!=0) {
                sum += Math.pow(-2, i);
            }
        }
        return sum;
    }

    public long value(List<Integer> l) {
        long sum = 0;
        for (int i=0;i < l.size(); i++) {
            int item = l.get(i);
            if (item != 0) {
                sum += Math.pow(-2, i);
            }
        }
        return sum;
    }

    int[] listToArray(List<Integer> list) {
        Integer[] intArr = list.toArray(new Integer[list.size()]);
        int[] toReturn = new int[intArr.length];
        for (int i = 0; i < intArr.length; i++) {
            toReturn[i] = intArr[i];
        }
        return toReturn;
    }


    //Try BFS
    private List<Integer> checkMatches(final long num) {
        Queue<List<Integer>> paths = new ArrayDeque<>();


        ArrayList<Integer> path1 = new ArrayList<>();
        path1.add(0);
        paths.add(path1);

        ArrayList<Integer> path2 = new ArrayList<>();
        path2.add(1);
        paths.add(path2);

        while(!paths.isEmpty()) {
            List<Integer> currPath = paths.remove();
            if (value(currPath) == num) {
                return currPath;
            }
            ArrayList<Integer> npath1 = new ArrayList<>(currPath);
            npath1.add(0);
            paths.add(npath1);

            ArrayList<Integer> npath2 = new ArrayList<>(currPath);
            npath2.add(1);
            paths.add(npath2);
        }
        return Collections.emptyList();
    }
}