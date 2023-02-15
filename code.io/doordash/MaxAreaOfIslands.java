package doordash;

import java.util.ArrayList;
import java.util.List;

public class MaxAreaOfIslands {

    record Pair(int x, int y){};

    public int maxArea(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int result = Integer.MIN_VALUE;
        for(int i =0; i< m; i++){
            for(int j=0; j < n; j++){
                if(grid[i][j] != 0){
                    result = Math.max(result,dfs(grid, i, j, m, n));
                }
            }
        }
        return result==Integer.MIN_VALUE?0:result;
    }

    public int dfs(int[][] grid, int x, int y, int m, int n){
        grid[x][y] = 0;
        int count = 0;
        List<Pair> neighbours = getNeighbours(x,y,m,n);
        for(Pair p: neighbours) {
            if (grid[p.x][p.y] != 0){
                count+= dfs(grid, p.x, p.y, m, n);
            }
        };
        return count+1;
    }

    public List<Pair> getNeighbours(int x, int y, int m, int n){
        List<Pair> ne = new ArrayList<>();
        if(x+1 <m){
            ne.add(new Pair(x+1, y));
        }
        if(y+1 < n){
            ne.add(new Pair(x, y+1));
        }
        if(x-1 >= 0){
            ne.add(new Pair(x-1, y));
        }
        if(y-1 >= 0){
            ne.add(new Pair(x, y-1));
        }
        return ne;
    }

    public static void main(String[] args){
        int[][] grid = {{0,0}};
        System.out.println(new MaxAreaOfIslands().maxArea(grid));
    }


}