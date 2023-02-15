package leetcode.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
* 1197. Minimum Knight Moves
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal
* direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].
*  It is guaranteed the answer exists.



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
Difficulty:
Medium
Lock:
*/
public class MinimumKnightMoves {
    /*static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }*/
    private record Point(int x, int y){}
    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {

        // Write your code here.
        int[][] board = new int[rows][cols];
        Deque<Point> q = new LinkedList<>();
        Point p = new Point(start_row,start_col);
        int[][] visited = new int[rows][cols];
        q.push(p);
        while(!q.isEmpty()){
            Point t = q.pop();
            if(t.x==end_row && t.y == end_col){
                return visited[t.x][t.y];
            }
            for(Point n: findNeighbours(t,rows,cols,visited)){
                visited[n.x][n.y] = visited[t.x][t.y]+1;
                q.push(n);
            }
        }
        return -1;
    }
    static List<Point> findNeighbours(Point p, int rows, int cols, int [][] visited){
        int[][] moves = new int[][]{{1,2},{1,-2},{2,1},{2,-1},{-1,2},{-1,-2},{-2,1},{-2,-2}};
        List<Point> ne = new ArrayList<>();
        for(int[] move: moves){
            int new_x = p.x + move[0];
            int new_y = p.y + move[1];
            if(ifValidPosition(new_x, new_y, rows, cols) && visited[new_x][new_y]==0){
                ne.add(new Point(new_x, new_y));
            }
        }
        return ne;
    }
    static boolean ifValidPosition(int x, int y, int rows, int cols){
        return x>=0 && x<rows && y>=0 && y<cols;
    }
}
