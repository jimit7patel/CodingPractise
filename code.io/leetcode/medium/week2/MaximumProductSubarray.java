package leetcode.medium.week2;
/*
* 152. Maximum Product Subarray
Medium

13453

399

Add to List

Share
Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
*/
public class MaximumProductSubarray {
    /*Intution: Since we have to find the contiguous subarray having maximum product then your approach should be combination of following three cases :

Case1 :- All the elements are positive : Then your answer will be product of all the elements in the array.
Case2 :- Array have positive and negative elements both :
If the number of negative elements is even then again your answer will be complete array because on multiplying all the negative numbers it will become positive.
If the number of negative elements is odd then you have to remove just one negative element and for that u need to check your subarrays to get the max product.
Case3 :- Array also contains 0 : Then there will be not much difference...its just that your array will be divided into subarray around that 0.
What u have to so is just as soon as your product becomes 0 make it 1 for the next iteration,
now u will be searching new subarray and previous max will already be updated.
*(These cases are much clear in approach 3)
** As it is said "Talk is Cheap, Show me the Code", so based on above discussion we can frame our code in many different ways, out of which I have mentioned 3 intutive approaches.

     */

    /*approach1
    For each index i keep updating the max and min. We are also keeping min because on multiplying with any negative number
    your min will become max and max will become min. So for every index i we will take max of (i-th element, prevMax * i-th element, prevMin * i-th element).

    * */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int max = nums[0];
        int min = nums[0];
        int r = max;
        for (int i = 1; i < nums.length; i++) {
            int t = max;
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(min * nums[i], t * nums[i]));
            r = Math.max(r,max);
        }
        return r;
    }
    //approach 3 two pointers more intuitive approach
    /*
    l and r, if all positive or negative even then at last iteration, we have all element product.
    if odd negative, we need to remove one negative from left or right side, so ans could be
    either left prefix or right suffix of that negative number which we get
    if 0, we reset to 1
     * */
    public int approach3(int[] nums){
        int l=1,r=1;
        int result = nums[0];
        for(int i=0; i< nums.length; i++){
            l *= nums[i];
            r *= nums[nums.length-1-i];
            result = Math.max(result, Math.max(l, r));
            if(nums[i] ==0){
                l = 1;
            }
            if(nums[nums.length-1-i] == 0){
                r = 1;
            }
        }
        return result;


    }
    //as you know when negative number, min becomes max, and max becomes min
    public int maxProductApproach2(int[] nums) {
        if (nums.length == 0) return 0;

        int max = nums[0];
        int min = nums[0];
        int r = max;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                max = min;
                min = max;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);

            r = Math.max(r,max);
        }
        return r;
    }

}
