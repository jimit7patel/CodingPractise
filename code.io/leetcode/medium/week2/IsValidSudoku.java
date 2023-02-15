package leetcode.medium.week2;

import java.util.HashSet;
import java.util.Set;

/*
* 36. Valid Sudoku
Medium

6269

789

Add to List

Share
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
*/
public class IsValidSudoku {
    //below is faster
    //approach is simple, just make sure for same row, not same digit,
    //same for same col and same cube
    public boolean isValidSudoku(char[][] board) {

        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] cube = new boolean[9][9];

        for(int i=0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                int d = board[i][j] - '1';

                if(row[i][d] || col[d][j] || cube[(i/3)*3 + j/3][d]){
                    return false;
                }
                row[i][d] = col[d][j] = cube[(i/3)*3 + j/3][d] = true;
            }
        }
        return true;
    }

    /*
    * Collect the set of things we see, encoded as strings. For example:

    '4' in row 7 is encoded as "(4)7".
    '4' in column 7 is encoded as "7(4)".
    '4' in the top-right block is encoded as "0(4)2".
    Scream false if we ever fail to add something because it was already added (i.e., seen before).*/
    public boolean usingEncoding(char[][] board){
       Set<String> seen = new HashSet<>();

        for(int i=0; i< board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                String s = "(" + board[i][j] + ")";
                if(!seen.add(s+i) || !seen.add(j + s) || !seen.add(i/3+s+j/3)){
                    return false;
                }
            }
        }
        return true;
    }

}
