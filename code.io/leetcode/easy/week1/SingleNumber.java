package leetcode.easy.week1;
/*
* Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.
Example 1:

Input: nums = [2,2,1]
Output: 1

*/
public class SingleNumber {

    public int singleNumber(int[] nums) {
        /*
        * 2 1 2 1 3 4 5 3 4
        * 2 ^ 2 1 ^ 1 3 ^ 3 4 ^ 4 5
        * x xor x = 0, 0 ^ x = x
        * */
        int ans = 0;
        for(int num: nums){
            ans = ans ^ num;
        }
        return ans;
    }

}
