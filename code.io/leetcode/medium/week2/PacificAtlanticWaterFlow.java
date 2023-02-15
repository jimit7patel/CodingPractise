package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*417. Pacific Atlantic Water Flow
Medium

5597

1053

Add to List

Share
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.



Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.*/
public class PacificAtlanticWaterFlow {
    public List<List<Integer>> usingBFS(int[][] heights){
        List<List<Integer>> result = new ArrayList<>();

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] atlantic = new boolean[m][n];
        boolean[][] pacific = new boolean[m][n];
        Deque<int[]> pacificQueue = new LinkedList<>();
        Deque<int[]> atlanticQueue = new LinkedList<>();

        for(int i=0; i<m; i++){
            pacificQueue.addLast(new int[]{i,0});
            pacific[i][0]= true;
            atlanticQueue.addLast(new int[]{i,n-1});
            atlantic[i][n-1] = true;
        }
        for(int i=0; i<n; i++){
            pacificQueue.addLast(new int[]{0,i});
            pacific[0][i] = true;
            atlanticQueue.addLast(new int[]{m-1,i});
            atlantic[m-1][i] = true;
        }

        bfs(heights, pacific, pacificQueue);
        bfs(heights, atlantic, atlanticQueue);

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(atlantic[i][j] && pacific[i][j]){
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;

    }
    public void bfs(int[][] heights, boolean[][] visited, Deque<int[]> q){
        int[][] dir = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        while(!q.isEmpty()){
            int[] p = q.removeFirst();
            for(int[] d: dir){
                int x = p[0] + d[0];
                int y = p[1] + d[1];

                if(x < 0 || x >= heights.length || y <0 || y >= heights[0].length || visited[x][y] || heights[p[0]][p[1]] > heights[x][y]){
                    continue;
                }
                q.addLast(new int[]{x,y});
                visited[x][y]=true;
            }
        }
    }
    public List<List<Integer>> usingDFS(int[][] heights){
        List<List<Integer>> result = new ArrayList<>();

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] atlantic = new boolean[m][n];
        boolean[][] pacific = new boolean[m][n];

        for(int i=0; i<m; i++){
            dfs(heights, pacific, i, 0);
            dfs(heights, atlantic, i, n-1);
        }
        for(int i=0; i<n; i++){
            dfs(heights, pacific, 0, i);
            dfs(heights, atlantic, m-1, i);
            pacific[0][i] = true;
            atlantic[m-1][i] = true;
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(atlantic[i][j] && pacific[i][j]){
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;

    }
    public void dfs(int[][] heights, boolean[][] visited, int x, int y){
        int[][] dir = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        visited[x][y] = true;
        for(int[] d: dir){
            int new_x = x + d[0];
            int new_y = y + d[1];
            if(new_x < 0 || new_x >= heights.length || new_y <0 || new_y >= heights[0].length || visited[new_x][new_y] || heights[x][y] > heights[new_x][new_y]){
                continue;
            }
            dfs(heights, visited, new_x, new_y);
        }
    }

}
