package leetcode.easy.week1;
/*
* 704. Binary Search
Easy

5577

125

Add to List

Share
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.*/
public class BinarySearch {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length;

        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target ){
                start = mid + 1;
            }else{
                end = mid - 1 ;
            }

        }
        return -1;
    }
}
