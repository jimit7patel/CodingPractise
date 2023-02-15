package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 51. N-Queens
Hard
9.1K
201
Companies
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.



Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]


Constraints:

1 <= n <= 9
*/
public class NQueen {
    //backtracking, gist is this logic to seeIf valid position
    //int diff = Math.abs(colPlacement.get(i) - colPlacement.get(rowID));
    //            if (diff == 0 || diff == rowID - i) {
    //                return false;
    //            }
    //time space complexityIs O(n!) exponentinal
    //
    public List<List<String>> solveNQueens(int n) {

        List<List<Integer>> result = new ArrayList<>();
        List<List<String>> finalResult = new ArrayList<>();
        solveNQueens(n, 0, new ArrayList<Integer>(), result);
        for(List<Integer> a: result){
            finalResult.add(toTextRepresentation(a));
        }
        return finalResult;

    }
    private static void solveNQueens(int n, int row, List<Integer> colPlacement,
                                     List<List<Integer>> result) {
        if (row == n) {
            // All queens are legally placed.
            result.add(new ArrayList<>(colPlacement));
        } else {
            for (int col = 0; col < n; ++col) {
                colPlacement.add(col);
                if (isValid(colPlacement)) {
                    solveNQueens(n, row + 1, colPlacement, result);
                }
                colPlacement.remove(colPlacement.size() - 1);
            }
        }
    }
    // test.Test if a newly placed queen will conflict any earlier queens
    // placed before.
    private static boolean isValid(List<Integer> colPlacement) {
        int rowID = colPlacement.size() - 1;
        for (int i = 0; i < rowID; ++i) {
            int diff = Math.abs(colPlacement.get(i) - colPlacement.get(rowID));
            if (diff == 0 || diff == rowID - i) {
                return false;
            }
        }
        return true;
    }
    // @exclude

    private static List<String> toTextRepresentation(List<Integer> colPlacement) {
        List<String> sol = new ArrayList<>();
        for (int aColPlacement : colPlacement) {
            char[] line = new char[colPlacement.size()];
            Arrays.fill(line, '.');
            line[aColPlacement] = 'Q';
            sol.add(new String(line));
        }
        return sol;
    }
}
