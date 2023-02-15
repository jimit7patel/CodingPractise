package leetcode.medium.week1;

import java.util.ArrayList;
import java.util.List;

/*
* 200. Number of Islands
Medium

15488

362

Add to List

Share
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
*/
public class NumberOfIslands {
    private static final int[][] DIR = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
    public int numIslands(char[][] grid) {
        int ans = 0;
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[i].length; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    // Traverse depth first starting from node x, mark all visited nodes visited.
    static void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        for(int[] d: DIR){
            int n_x = i + d[0];
            int n_y = j + d[1];
            if(n_x >=0 && n_x < grid.length && n_y >=0 && n_y < grid[0].length && grid[n_x][n_y] != '0'){
                dfs(grid, n_x, n_y);
            }
        }
    }
}
