package leetcode.medium;
/*221. Maximal Square
Medium
8.4K
181
Companies
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.



Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
*/
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        return dp(matrix);
    }
    public int dp(char[][] matrix){
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        int ans = 0;
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    dp[i+1][j+1] = Math.min(Math.min(dp[i+1][j], dp[i][j+1]),dp[i][j])+1;
                    ans = Math.max(dp[i+1][j+1], ans);
                }
            }
        }
        return ans;
    }

    //bruteforce is O(n*m)^2 worse case if all are 1
    public int bruteForce(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int count = 0;
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int k = 1;
                    boolean flag = true;
                    while (j + k < matrix[0].length && i + k < matrix.length && flag) {
                        for (int m = j; m <= j + k; m++) {
                            if (matrix[i + k][m] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        for (int m = i; m <= i + k; m++) {
                            if (matrix[m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag)
                            k++;
                    }
                    count = (k) * (k);
                    if (count > result)
                        result = count;
                }
            }
        }
        return result;
    }
}
