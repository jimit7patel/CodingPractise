package leetcode.medium;
/*
* 7. Reverse Integer
Medium
9.3K
11.3K
Companies
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
*/
public class ReverseInteger {
    //theProblem of outside of 32 bit is not trivial but I dont think it is important problem to solve
    public int reverse(int x) {
        int ans = 0;
        while(x !=0){
            int d = x%10;
            x = x/10;
            //this conditionCheck is to make sure it isNot outside of range.
            if (ans > Integer.MAX_VALUE/10 || (ans == Integer.MAX_VALUE / 10 && d > 7)) return 0;
            if (ans < Integer.MIN_VALUE/10 || (ans == Integer.MIN_VALUE / 10 && d < -8)) return 0;
            ans = (ans * 10) + d;
        }
        return ans;
    }

}
