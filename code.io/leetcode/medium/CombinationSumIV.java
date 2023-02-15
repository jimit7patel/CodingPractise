package leetcode.medium;
/*
* 377. Combination Sum IV
Medium
5.4K
556
Companies
Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.



Example 1:

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Example 2:

Input: nums = [9], target = 3
Output: 0
*/
public class CombinationSumIV {
        public int combinationSum4(int[] nums, int target) {
            return dp(nums, target, 0);
            //return helperAnother(nums, target, 0);
        }
        public int helper(int[] nums, int target, int s, int i){
            if(s > target){
                return 0;
            }
            if(s == target){
                return 1;
            }
            int ans = 0;
            if(i<nums.length-1){
                ans +=helper(nums, target, s , i+1);
            }
            ans +=helper(nums, target, s + nums[i], 0);

            return ans;
        }
        public int helperAnother(int[] nums, int target, int s){
            if(target==s){
                return 1;
            }
            int ans = 0;
            for(int i=0; i<nums.length; i++){
                if(s+nums[i]<=target){
                    ans+=helperAnother(nums, target, s+nums[i]);
                }
            }
            return ans;
        }
        public int dp(int[] nums, int target, int s){
            int[] dp = new int[target+1];
            dp[0] = 1;
            for(int i=1; i<=target; i++){
                for(int num: nums){
                    if(num<=i){
                        dp[i]+=dp[i-num];
                    }
                }
            }
            return dp[target];
        }
}
