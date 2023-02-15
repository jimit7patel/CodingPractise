package leetcode.medium.week1;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

/*
* https://leetcode.com/problems/01-matrix/
* Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

The soln: this seems to be graph traversal problem, bfs or dfs and keep checking distance.
*/
public class UpdateMatrix {

    public int[][] updateMatrix(int[][] mat) {
        return dp(mat);
        //return bfs(mat);
        //return bfs1TLE(mat);
    }
    public int[][] dp(int[][] mat){
        int[][] ans = new int[mat.length][mat[0].length];
        IntStream.range(0,mat.length)
                .forEach(r -> IntStream.range(0, mat[0].length).forEach(c -> ans[r][c]=Integer.MAX_VALUE - 100000));

        for(int i=0; i< mat.length; i++){
            for(int j=0; j< mat[0].length; j++){
                if(mat[i][j] != 0){
                    if(i>0){
                        ans[i][j] = Math.min(ans[i][j], ans[i - 1][j] + 1);
                    }
                    if(j > 0){
                        ans[i][j] = Math.min(ans[i][j], ans[i][j-1] + 1);
                    }
                }else{
                    ans[i][j]=0;
                }
            }
        }
        for(int i=mat.length-1; i>=0; i--){
            for(int j=mat[0].length-1; j>=0; j--){
                if(i<mat.length-1){
                    ans[i][j] = Math.min(ans[i][j], ans[i + 1][j] + 1);
                }
                if(j < mat[0].length-1){
                    ans[i][j] = Math.min(ans[i][j], ans[i][j+1] + 1);
                }
            }
        }
        return ans;
    }
    public int[][] bfs1TLE(int[][] mat){
        int[][] ans = new int[mat.length][mat[0].length];
        for(int i=0;i<mat.length; i++){
            for(int j=0; j<mat[0].length; j++){
                if(mat[i][j] == 1){
                    helper(mat, i, j, ans);
                }
            }
        }
        return ans;
    }

    public int[][] bfs(int[][] mat){
        int[][] ans = new int[mat.length][mat[0].length];
        Deque<int[]> q = new LinkedList<>();
        for(int i=0; i< mat.length; i++){
            for(int j=0; j<mat[0].length; j++){
                if(mat[i][j] == 0){
                    q.addLast(new int[]{i,j});
                }else{
                    ans[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while(!q.isEmpty()){
            int[] t = q.removeFirst();
            int[][] dir = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
            for(int i=0; i<4; i++){
                int n_r = t[0]+dir[i][0];
                int n_c = t[1]+dir[i][1];
                if(n_r >=0 && n_r < mat.length && n_c >=0 && n_c < mat[0].length){
                    if(ans[n_r][n_c] > ans[t[0]][t[1]]+1){
                        ans[n_r][n_c] = ans[t[0]][t[1]] + 1;
                        q.addLast(new int[]{n_r,n_c});
                    }
                }
            }
        }
        return ans;
    }
    public void helper(int[][] mat, int i, int j, int[][] ans){
        Deque<int[]> q = new LinkedList<>();
        q.addLast(new int[]{i,j});
        int d = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0; k< size; k++) {
                int[] t = q.removeFirst();
                if (mat[t[0]][t[1]] == 0) {
                    ans[i][j] = d;
                    return;
                }
                if(t[0] >= 1){
                    q.addLast(new int[]{t[0]-1,t[1]});
                }
                if(t[1] >= 1){
                    q.addLast(new int[]{t[0],t[1]-1});
                }
                if(t[0] < mat.length-1){
                    q.addLast(new int[]{t[0]+1,t[1]});
                }
                if(t[1] < mat[0].length -1){
                    q.addLast(new int[]{t[0],t[1]+1});
                }
            }
            d++;
        }
    }

    public static void main(String[] args){
        int[][] res = new UpdateMatrix().updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}});
        System.out.println(res);
    }
}
