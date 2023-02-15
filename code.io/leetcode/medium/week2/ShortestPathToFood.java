package leetcode.medium.week2;

import java.util.Deque;
import java.util.LinkedList;

/*
* Food
Posted on March 13, 2021 · 2 minute read

Formatted question description: https://leetcode.ca/all/1730.html

1730. Shortest Path to Get Food
Level
Medium

Description
You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:

'*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

Example 1:

Image text

Input: grid = [[“X”,”X”,”X”,”X”,”X”,”X”],[“X”,”*”,”O”,”O”,”O”,”X”],[“X”,”O”,”O”,”#”,”O”,”X”],[“X”,”X”,”X”,”X”,”X”,”X”]]

Output: 3

Explanation: It takes 3 steps to reach the food.

Example 2:

Image text

Input: grid = [[“X”,”X”,”X”,”X”,”X”],[“X”,”*”,”X”,”O”,”X”],[“X”,”O”,”X”,”#”,”X”],[“X”,”X”,”X”,”X”,”X”]]

Output: -1

Explanation: It is not possible to reach the food.

Example 3:

Image text

Input: grid = [[“X”,”X”,”X”,”X”,”X”,”X”,”X”,”X”],[“X”,”*”,”O”,”X”,”O”,”#”,”O”,”X”],[“X”,”O”,”O”,”X”,”O”,”O”,”X”,”X”],[“X”,”O”,”O”,”O”,”O”,”#”,”O”,”X”],[“X”,”X”,”X”,”X”,”X”,”X”,”X”,”X”]]

Output: 6

Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.

Example 4:

Input: grid = [[“O”,”*”],[”#”,”O”]]

Output: 2

Example 5:

Input: grid = [[“X”,”*”],[”#”,”X”]]

Output: -1

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[row][col] is '*', 'X', 'O', or '#'.
The grid contains exactly one '*' */
public class ShortestPathToFood {

    public int getFood(char[][] grid){
        int r = grid.length;
        int c = grid[0].length;
        int startRow;
        int startCol;

        for(int i=0; i< r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j] == '*'){
                    startCol=i;
                    startCol=j;
                    break;
                }
            }
        }

        Deque<int[]> q = new LinkedList<>();
        int[][] moves = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int distance = 0;
        while(q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int[] cur = q.removeFirst();
                for(int[] m: moves){
                    int new_r = cur[0] + m[0];
                    int new_c = cur[1] + m[1];
                    if(new_r <0 || new_r >r || new_c <0 || new_c >c){ //out of boundary
                        continue; //skip the rest;
                    }
                    if(grid[new_r][new_c] == '#'){//food cell
                        return distance+1;
                        //this is because I am not using visited boolean array. I am inverting to obstacle, so if food, it would have been inverted too.
                        // so, I have to check if dest found.
                        //so, return here, else, I would check in current point and return there.
                    }
                    if(grid[new_r][new_c] != 'X'){ //not an obstacle
                        grid[new_r][new_c] = 'X'; //mark as visited
                        q.addLast(new int[]{new_r, new_c});
                    }
                }
            }
            distance++;
        }

        return distance;
    }
}
