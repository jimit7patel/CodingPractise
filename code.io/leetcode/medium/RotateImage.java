package leetcode.medium;
/*
* 48. Rotate Image
Medium
13.2K
609
Companies
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
*/
public class RotateImage {
    public void rotate(int[][] matrix) {
        approach2(matrix);
        //approach1(matrix);
    }
    public void approach2(int[][] matrix){
        for(int i=0; i<(matrix.length+1)/2; i++){
            for(int j=i; j<matrix.length-1-i; j++){
                int top = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i]=matrix[matrix.length-1-i][matrix.length-1-j];
                matrix[matrix.length-1-i][matrix.length-1-j]=matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i]=top;
            }
        }
    }
    public void approach1(int[][] matrix) {
        //first transpose this
        for(int i=0; i<matrix.length; i++){
            for(int j=i; j<matrix.length; j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        //now reverse this
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<(matrix.length)/2; j++){
                int t = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-j-1];
                matrix[i][matrix.length-j-1] = t;
            }
        }
    }

}
