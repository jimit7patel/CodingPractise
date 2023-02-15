package leetcode.easy.week1;
/*
* You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
*/
public class ClimbingStair {
    public int climbStairs(int n) {
        //f(n) = f(n-1) + f(n-2)
        //f(0) = 1; f(1) = 1; f(2) = 2;
        int p = 0, p_1 =1, p_2 =1;
        if(n ==1 || n==0){
            return 1;
        }
        for(int i=2; i<=n; i++){
            p = p_1 + p_2;
            p_2 = p_1;
            p_1 = p;
        }
        return p;
    }
}
