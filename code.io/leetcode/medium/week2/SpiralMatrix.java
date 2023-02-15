package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.List;

/*
* 54. Spiral Matrix
Medium

8591

900

Add to List

Share
Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
*/
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int left=0,right =matrix[0].length-1;
        int top = 0, bottom = matrix.length-1;
        //the problem is simple, just make sure to have limit as left, right, top, bottom,
        //and for the bottom and right make sure to put a condition so that it does not print same

        while(left<=right && top <=bottom){
            for(int i=left; i<= right; i++){
                result.add(matrix[top][i]);
            }
            top++;
            for(int i=top; i<= bottom; i++){
                result.add(matrix[i][right]);
            }
            right--;

            if(top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if(left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }
}
