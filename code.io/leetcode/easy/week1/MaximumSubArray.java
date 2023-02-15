package leetcode.easy.week1;
/*
* Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

A subarray is a contiguous part of an array.



Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
*/
public class MaximumSubArray {
    public int maxSubArray(int[] nums) {
        //dp solution
        int ans = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i=1; i< nums.length; i++){
            dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
            ans = Math.max(ans, dp[i]);
        }
        //return ans;
        //same code but if we replace dp array with simple variable
        int prevSum = nums[0];
        for(int i=1; i< nums.length; i++){
            prevSum = nums[i] + (prevSum > 0 ? prevSum : 0);
            ans = Math.max(ans, prevSum);
        }
        return ans;
        //another approach divide and conquer and I need to learn this, it is tricky
    }


}
