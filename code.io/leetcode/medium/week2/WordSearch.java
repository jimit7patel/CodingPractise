package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* 79. Word Search
Medium

10638

395

Add to List

Share
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
*/
public class WordSearch {
    Set<List<Integer>> visited = new HashSet<>();

    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    if(helper(board, word, i, j, 0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean helper(char[][] board, String word, int i, int j, int idx){
        if (board[i][j] != word.charAt(idx)) {
            return false;
        }
        if(idx == word.length()-1) {
            return true;
        }
        List<Integer> c = Arrays.asList(i, j);
        visited.add(c);
        for(List<Integer> p: getNeighbours(i, j, board)){
            if(!visited.contains(p)){
                if(helper(board, word, p.get(0), p.get(1), idx+1)){
                    return true;
                }
            }
        }
        visited.remove(c);
        return false;
    }

    public List<List<Integer>> getNeighbours(int i, int j, char[][] board){
        List<List<Integer>> result = new ArrayList<>();
        if(i+1 < board.length){
            result.add(Arrays.asList(i+1, j));
        }
        if(j+1 < board[0].length){
            result.add(Arrays.asList(i, j+1));
        }
        if(i-1 >=0 ){
            result.add(Arrays.asList(i-1, j));
        }
        if(j-1 >=0){
            result.add(Arrays.asList(i, j-1));
        }
        return result;
    }
    public static void main(String[] args){
        char[][] board = new char[][]{{'A','B','C','E'}, {'S','F','C','S'},{'A','D','E','E'}};
        char[][] board1 = new char[][]{{'A'}};
        String word = "A";
        new WordSearch().exist(board, word);


    }
}
class WordSearchSimplerCoding{
    private static int m, n;
    private static final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0)
            return true;

        m = board.length;
        n = board[0].length;
        char firstChar = word.charAt(0);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == firstChar) {
                    if (searchFrom(i, j, board, word, 0))
                        return true;
                }
            }
        }

        return false;
    }

    private static boolean searchFrom(int x, int y, char[][] board, String word, int wi) {
        // Acception case, leaf.
        if (wi+1 == word.length())
            return true;
        board[x][y] = '-';
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n || word.charAt(wi+1) != board[nx][ny])
                continue;

            if (searchFrom(nx, ny, board, word, wi + 1))
                return true;


        }
        board[x][y] = word.charAt(wi);

        return false;
    }
}
