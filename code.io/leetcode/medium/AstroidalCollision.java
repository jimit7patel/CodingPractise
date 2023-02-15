package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/*
* 735. Asteroid Collision
Medium
4.5K
386
Companies
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.



Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.


Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
*/
public class AstroidalCollision {

    //idea is simple, if postive, you add toStack,
    //if negative then see if you canCollide withPostive from stack.
    //then if negative is left, add to stackAnd it canNot collide any more since
    //new positive can only go to right.
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        while(i<asteroids.length){
            int ast = asteroids[i];
            if(ast > 0 || stack.isEmpty() || stack.getFirst() < 0){
                stack.addFirst(ast);
            }else{
                int p = stack.removeFirst();
                if (p > Math.abs(ast)) {
                    stack.addFirst(p);
                }else if(p < Math.abs(ast)){
                    i--;
                }
            }
            i++;
        }
        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }
}
