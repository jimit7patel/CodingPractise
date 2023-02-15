package leetcode.easy.week1;

import java.util.Arrays;

/*
* Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.



Example 1:

Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
*/
public class MissingNumber {
    public int missingNumber(int[] nums) {
        return approach1(nums);
    }
    public int approach1(int[] nums){
        int sum = Arrays.stream(nums).reduce((a,b) -> a+b).getAsInt();
        int sum1 = Arrays.stream(nums).sum();
        int n = nums.length;
        int expected = n * (n+1)/2;
        return (expected - sum);
    }
    public int approach2(int[] nums){
        int ans = 0;

        for(int i=0; i<nums.length; i++){
            ans = ans ^ i ^ nums[i];
        }
        return ans ^ nums.length;
    }
}
