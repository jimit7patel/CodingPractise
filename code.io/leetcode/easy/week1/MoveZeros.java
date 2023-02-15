package leetcode.easy.week1;

import java.util.Arrays;

/*
* Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.
Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

*/
public class MoveZeros {
    static int[] nums = new int[]{0,1,2,0,3,4,0};
    public void moveZeroes(int[] nums) {
        int zero = 0;
        for(int i=0; i< nums.length; i++){
            if(nums[i] != 0){
                swap(nums, i, zero);
                zero++;
            }
        }
        return;

    }
    public void swap (int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public static void main(String[] args){
      new MoveZeros().moveZeroes(nums);
        Arrays.stream(nums).forEach(i -> System.out.println(i));
    }
}
