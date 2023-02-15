package leetcode.hard.knowforfun;
/*
* 37. Sudoku Solver
Hard
7.1K
189
Companies
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.



Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],
* [".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],
* ["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],
* [".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],
* [".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],
* ["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],
* ["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],
* ["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],
* ["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.*/
public class SudokuSolver {
    //the problem is not that difficult, it is simple backtracking
    //index manipulation like for block, i/3*3 + j/3  if using seen array
    //if not using seen array and checking entire thing
    // for(int i = 0; i < 9; i++) {
    //            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
    //            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
    //            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
    //board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
    //        }
    //        return true;
    //the weird thing is if you do not run the solve fn from scratch each time
    //and pass (i,0) it works but if you pass(i,j+1) does Not work
    private boolean[][] row = new boolean[9][9];
    private boolean[][] col = new boolean[9][9];
    private boolean[][] block = new boolean[9][9];
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        int num, k;
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if(board[i][j]!='.') {
                    num = board[i][j]-'1';
                    k = i/3*3 + j/3;
                    row[i][num] = col[j][num] = block[k][num] = true;
                }
            }
        }
        solve(board);
    }
    public boolean solve(char[][] board){

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell
                            row[i][c-'1'] = col[j][c-'1']=block[(i/3)*3 + j/3][c-'1'] =true;
                            if(solve(board))
                                return true; //If it's the solution return true
                            board[i][j] = '.'; //Otherwise go back
                            row[i][c-'1'] = col[j][c-'1']=block[(i/3)*3 + j/3][c-'1'] =false;

                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }
    private boolean isValid(char[][] board, int i, int j, char c){
        if(row[i][c-'1'] || col[j][c-'1'] || block[(i/3)*3 + j/3][c-'1']){
            return false;
        }
        return true;
    }

    /*
    no need for this as I am using boolean array to store seen
    private boolean isValid3(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }
    */
}
