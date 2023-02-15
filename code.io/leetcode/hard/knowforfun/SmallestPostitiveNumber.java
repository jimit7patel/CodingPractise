package leetcode.hard.knowforfun;
/*
* 41. First Missing Positive
Hard
12.7K
1.5K
Companies
Given an unsorted integer array nums, return the smallest missing positive integer.

You must implement an algorithm that runs in O(n) time and uses constant extra space.



Example 1:

Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.

there is a template of cyclic sort, use that to solve all missing number from range 1 to n problems.
https://www.youtube.com/watch?v=JfinxytTYFQ&t=3491s
*/

public class SmallestPostitiveNumber {
    public int firstMissingPositive(int[] nums) {
        int i = 0, n = nums.length;
        while (i <= n) {
            // If the current value is in the range of (0,length) and it's not at its correct position,
            // swap it to its correct position.
            // Else just continue;
            int current = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= n && nums[current] != nums[i])
                swap(nums, i, current);
            else
                i++;
        }

        for(int j=0; j<n; j++){
            if(nums[j] != j+1){
                return j+1;
            }
        }
        return n+1;

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }
}
