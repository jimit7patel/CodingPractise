package leetcode.medium.week2;
/*
* 33. Search in Rotated Sorted Array
Medium

16567

1007

Add to List

Share
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
*/
public class SearchInSortedArray {
    public int search(int[] nums, int target) {
        boolean ifOrange = false;
        int l = nums.length-1;
        if(target <= nums[l]){
            ifOrange = true;
        }
        int low = 0;
        int high = l;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] > nums[l]){ // mid in green zone
                if(ifOrange || (target > nums[mid])){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }else if (!ifOrange || (target < nums[mid])) { //correct zone
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
        }
        return -1;
    }
    public static void main(String[] args){
        System.out.println(new SearchInSortedArray().search(new int[]{4,5,6,7,0,1,2},0));
    }
}
