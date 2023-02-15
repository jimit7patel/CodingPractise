package leetcode.medium.week2;
/*
* Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/
public class SortColors {

    public void sortColors(int[] nums) {
        int zero = 0;
        int two = nums.length-1;
        int i = 0;
        while(i <= two){
            int a = nums[i];
            if(a == 0){
                swap(nums, i++, zero++);
            }else if(a == 2){
                swap(nums, i, two--);
            }else {
                i++;
            }
        }
    }
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
