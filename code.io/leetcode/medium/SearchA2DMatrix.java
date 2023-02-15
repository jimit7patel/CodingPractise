package leetcode.medium;
/*
* 74. Search a 2D Matrix
Medium
10.8K
323
Companies
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13

*/
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int end = row * col -1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(matrix[mid/col][mid%col] == target){
                return true;
            }else if(matrix[mid/col][mid%col] < target){
                start = start + 1;
            }else {
                end  = end - 1;
            }
        }
        return false;
    }
}
