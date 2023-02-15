package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* 73. Set Matrix Zeroes
Medium
10.1K
587
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.



Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
*/
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }

        }

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(rows.contains(i) || cols.contains(j)){
                   matrix[i][j]=0;
                }
            }

        }

    }
    //this is not very intuitive
    //use 0th row and 0th col to use as flag
    public void setZerosWithoutExtraSpace(int[][] matrix){
        boolean  col = false;
        for(int i=0; i<matrix.length; i++){
            if(matrix[i][0] ==0){
                col = true;
            }
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][j]==0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][0] ==0 || matrix[0][j] ==0){
                    matrix[i][j] = 0;
                }
            }
        }
        if(matrix[0][0] ==0){
            for(int j=0; j<matrix[0].length; j++){
                matrix[0][j] = 0;
            }
        }
        if(col){
            for(int i=0; i<matrix.length; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
