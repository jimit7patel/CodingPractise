package doordash;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * Question:
 * 317. Shortest Distance from All Buildings - Hard
 *
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:
Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 * */
public class ShortestDistanceFromAllBuilding {

    record Point (int x, int y, int distance){}
    int[][] distance;
    int[][] reach;
    public int shortestDistance(int[][] grid){


        distance = new int[grid.length][grid[0].length];
        reach = new int[grid.length][grid[0].length];
        int building = 0;
        int ans = Integer.MAX_VALUE;
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                if(grid[i][j] == 1){
                    building++;
                    bfs(grid, i, j);
                }
            }
        }

        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                if(reach[i][j] == building && grid[i][j] == 0){
                    ans = Math.min(ans,distance[i][j]);
                }
            }
        }

        return ans;

    }

    public void bfs(int[][] grid, int i, int j){
        Deque<Point> q = new ArrayDeque<>();
        q.addFirst(new Point(i, j, 0));
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        while(!q.isEmpty()){
            Point c = q.removeFirst();
            for(Point n: getNeighbour(i, j, grid.length, grid[0].length, c.distance, grid)){
                if(!visited[n.x][n.y]){
                    visited[n.x][n.y] = true;
                    distance[n.x][n.y] += c.distance+1;
                    reach[n.x][n.y]++;
                    q.addFirst(n);
                }
            }
        }
    }

    public List<Point> getNeighbour(int i, int j, int m, int n, int d, int[][] grid){
        List<Point> l = new ArrayList<>();
        if(i+1 <= m && grid[i+1][j] == 0){
            l.add(new Point(i+1, j, d+1));
        }
        if(i-1 >=0 && grid[i-1][j] == 0){
            l.add(new Point(i-1, j, d+1));
        }
        if(j+1 <=n && grid[i][j+1] == 0){
            l.add(new Point(i, j+1, d+1));
        }
        if(j-1 >=0 && grid[i][j-1] == 0){
            l.add(new Point(i, j-1, d+1));
        }
        return l;
    }


}
