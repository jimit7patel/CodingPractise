package leetcode.easy.week1;

import java.util.stream.IntStream;

/*
* Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.



Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
*/
public class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        return approach2(nums);
    }
    //compare first and last element as they are max and set it to
    //last and then decrement
    public int[] approach2(int[] nums){
        int[] ans = new int[nums.length];
        int first = 0;
        int second = nums.length-1;
        for(int i=nums.length-1; i>=0 ; i--){
            if(Math.abs(nums[first]) > Math.abs(nums[second])){
                ans[i] = nums[first] * nums[first];
                first++;
            }else{
                ans[i] = nums[second] * nums[second];
                second--;
            }
        }
        return ans;
    }
    /*
    * below is lengthy code*/
    public int[] approach1(int[] nums){
        int[] ans = new int[nums.length];
        int one = 0;
        int other = nums.length;
        for(int i=0; i< nums.length; i++){
            if(nums[i] >= 0){
                other = i;
                one = other - 1;
                break;
            }
        }
        one = other - 1;

        for(int i=0; i< nums.length; i++) {
            if (other < nums.length && one >= 0) {
                if (nums[other] > Math.abs(nums[one])) {
                    ans[i] = nums[one] * nums[one];
                    one--;
                } else {
                    ans[i] = nums[other] * nums[other];
                    other++;
                }
            }
            else if (one >= 0) {
                ans[i] = nums[one] * nums[one];
                one--;
            }
            else if (other < nums.length) {
                ans[i] = nums[other] * nums[other];
                other++;
            }
        }

        return ans;

    }
    public static void main(String[] args){
        IntStream.of(new SortedSquares().sortedSquares(new int[]{-1})).forEach( a -> System.out.println(a));
    }
}
