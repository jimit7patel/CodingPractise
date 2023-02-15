package leetcode.easy.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.

You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.

Return the modified image after performing the flood fill.



Example 1:


Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
* */
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc] != color){
            dfs(image,  sr,  sc, color, image[sr][sc]);
        }
        return image;
    }

    public void dfs(int[][] image, int i, int j, int newColor, int oldColor){
        image[i][j] = newColor;
        for(List<Integer> neighbour: getNeighbour(image, i, j)){
            int ni = neighbour.get(0);
            int nj = neighbour.get(1);
            if(image[ni][nj] == oldColor){
                dfs(image, ni, nj, newColor, oldColor );
            }
        }
    }

    public List<List<Integer>> getNeighbour(int[][] image, int r, int c){
        int m = image.length-1;
        int n = image[0].length-1;
        List<List<Integer>> neighbours = new ArrayList<>();
        int x[] = {-1,1};
        int y[] = {-1, 1};
        if(r+1 <=m){
            neighbours.add(Arrays.asList(r+1, c));
        }
        if(r-1 >= 0){
            neighbours.add(Arrays.asList(r-1, c));
        }
        if(c+1 <= n){
            neighbours.add(Arrays.asList(r, c+1));
        }
        if(c-1 >= 0){
            neighbours.add(Arrays.asList(r, c-1));
        }

        return neighbours;

    }
}
