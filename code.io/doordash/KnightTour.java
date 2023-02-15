package doordash;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.



Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]


Constraints:

|x| + |y| <= 300
Companies:
Expedia, Facebook, Amazon, Mathworks, Google, Indeed, Microsoft, Qualtrics, Cisco, Twitter

Related Topics:
Breadth-first Search


* */
public class KnightTour {
    public int find_min_moves(int x, int y){
        record Pair (int x, int y){}
        Deque<Pair> q = new ArrayDeque<>();
        Set<Pair> visited = new HashSet<>();
        q.addFirst(new Pair(0,0));
        visited.add(new Pair(0,0));
        int ans = 0;
        int step = 0;
        int[][] moves = new int[][]{{1,2},{1,-2},{-1,2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
        x = Math.abs(x);
        y = Math.abs(y);

        while(!q.isEmpty()) {
            step++;
            int l = q.size();
            for (int i = 0; i < l; i++) {
                Pair p = q.removeFirst();
                for (int[] d : moves) {
                    int new_x = p.x + d[0];
                    int new_y = p.y + d[1];
                    if (new_x == x && new_y == y) {// found the answer and return
                        return step;
                    }
                    Pair new_p = new Pair(new_x, new_y);
                    /*

                    /** https://www.programmersought.com/article/63865491006/

                    Why newX >= -1 && newY >= -1) instead of newX >= 0 && newY >= 0)?
                    The co-or in general to compute the knight moves are: (x-2, y-1) (x-2, y+1), (x-1, y-2)
                    where for all x,y>=2 the next “move” will always be >=0 (i.e. in the first quadrant).

                    Only for x=1/y=1, the next move may fall in the negative quad,
                    for example (x-2,y-1) or (x-1, y-2), and hence x=-1 y=-1 boundary is considered.

                    Firstly, it's symmetric for axes. So let x = abs(x), y = abs(y).

                    Secondly, attempts with x or y values smaller than -1 should be ignored.

                            For example, to reach (1,1) from (0,0), the best way is to get (2,-1) or (-1,2) first, then (1,1) (two steps). If we eliminate all coordinates with negative numbers, then we can't reach (1,1) from (0,0) within two steps.*/
                    if (!visited.contains(new_p) && new_x >= -1 && new_y >= -1) {
                        q.addLast(new_p);
                        visited.add(new_p);
                    }
                }
            }
        }
        return step;

    }
/*
    private Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();
    private int dp(int x, int y) {
        if (x + y == 0) // (0, 0)
            return 0;
        if (x + y == 2) // (0, 2), (1, 1), (2, 0)
            return 2;
        Pair<Integer, Integer> key = new Pair<>(x, y);
        if (memo.containsKey(key))
            return memo.get(key);
        final int minMove = Math.min(
                dp(Math.abs(x - 2), Math.abs(y - 1)),
                dp(Math.abs(x - 1), Math.abs(y - 2))) + 1;
        memo.put(key, minMove);
        return minMove;
    }
}*/

    public static void main(String[] args){
        System.out.println(new KnightTour().find_min_moves(5,5));
    }
}
