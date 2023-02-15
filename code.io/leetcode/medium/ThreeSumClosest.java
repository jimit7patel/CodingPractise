package leetcode.medium;

import java.util.Arrays;

/*
* 16. 3Sum Closest
Medium
8.4K
462
Companies
Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.



Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Example 2:

Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
*/
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;

        // Sort - nlogn
        Arrays.sort(nums);

        // Two pointer closing with fixed element
        int closest = Integer.MAX_VALUE;

        for (int i = 0; i <= n - 3; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int current_triplet_sum = nums[i] + nums[left] + nums[right];

                // Update
                if (Math.abs(target - closest) > Math.abs(target - current_triplet_sum)) {
                    closest = current_triplet_sum;
                }

                // Move pointers
                if (current_triplet_sum < target) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        return closest;
    }
}
