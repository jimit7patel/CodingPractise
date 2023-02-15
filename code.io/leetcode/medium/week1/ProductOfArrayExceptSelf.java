package leetcode.medium.week1;
/*
* 238. Product of Array Except Self
Medium

13882

795

Add to List

Share
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
*/
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] out = new int[nums.length];
        int prev = 1;
        for(int i=0; i< nums.length; i++){
            out[i] = prev;
            prev *= nums[i];
        }
        prev = 1;
        for(int i=nums.length-1; i>= 0; i--){
            out[i] *= prev;
            prev *= nums[i];
        }
        return out;
    }
}
