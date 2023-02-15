package leetcode.medium.week1;

import java.util.Deque;
import java.util.LinkedList;

/*
*
* 994. Rotting Oranges
Medium

7730

293

Add to List

Share
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
*/
public class RottingOranges {
    private static final int[][] DIR = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
    private static Deque<int[]> q = new LinkedList<>();
    int cnt = 0;
    int tot = 0;
    public int orangesRotting(int[][] grid) {
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[i].length; j++){
                if(grid[i][j] != 0){
                    tot++;
                }
                if(grid[i][j] == 2){
                    q.addLast(new int[]{i,j});
                }
            }
        }
        return bfs(grid);
    }

    public int bfs(int[][] grid) {
        int m =0;
        while(!q.isEmpty()) {
            int size = q.size();
            cnt += size;
            for(int k=0; k<size; k++) {
                int[] t = q.removeFirst();
                for (int[] l : DIR) {
                    int n_x = t[0] + l[0];
                    int n_y = t[1] + l[1];
                    if (n_x >= 0 && n_x < grid.length && n_y >= 0 && n_y < grid[0].length && grid[n_x][n_y] == 1) {
                        grid[n_x][n_y] = 2;
                        q.addLast(new int[]{n_x,n_y});
                    }
                }
            }
            if(!q.isEmpty()){
                m++;
            }
        }
        return tot == cnt ? m : -1;
    }
    public static void main(String[] args){
        System.out.println(new RottingOranges().orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
    }
}
