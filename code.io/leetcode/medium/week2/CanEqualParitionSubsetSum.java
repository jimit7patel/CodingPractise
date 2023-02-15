package leetcode.medium.week2;

import java.util.Arrays;

/*
* 416. Partition Equal Subset Sum
Medium

8521

136

Add to List

Share
Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.



Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
*/
public class CanEqualParitionSubsetSum {
    private boolean[][] dp;
    public boolean canPartition(int[] nums) {
        int s = Arrays.stream(nums).sum();
        if(s%2!=0){
            return false;
        }

        int target = s/2;

        int l = nums.length;
        dp = new boolean[l+1][target+1];

        return helper(nums, 0, target, 0);

    }
    public boolean dp(int[] nums, int target){
        dp[0][0] = true;
        for(int i=1; i<=nums.length; i++){
            for(int j=1; j<=target; j++){
                dp[i][j] = dp[i-1][j];
                if(nums[i-1] <= j){
                    dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i-1]];
                }
            }
        }

        return dp[nums.length][target];
    }
    public boolean helper(int[] nums, int i, int target, int l){
        if(target<0 || i>=nums.length || l == nums.length){
            return false;
        }
        if(target == 0){
            return true;
        }
        if(dp[i][target]){
            return false;
        }
        dp[i][target] = true; //this is visited
        if(helper(nums, i+1, target-nums[i], l+1)){
            return true;
        }
        return helper(nums, i+1, target, l);
    }
    public static void main(String[] args){
        System.out.println(new CanEqualParitionSubsetSum().canPartition(new int[]{1,5,11,5}));
    }
}
