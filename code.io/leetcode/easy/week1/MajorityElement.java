package leetcode.easy.week1;

import java.util.BitSet;

/*
* Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

*/
//so many approaches to solve this
//1. brute force, 2. hashmap 3. sort 4. random index 5. devide and conquer 6. Boyer-Moore Voting Algorithm
public class MajorityElement {
    public int majorityElement(int[] nums) {

        //lets understand Boyer-Moore Voting Algorithm
        //intution is, because majority element is more than n/2, there should be always count >=1 if you nullify with other elements
        /*algo: assume first is MJE, then set count = 1,
        compare that with next element, if different reduce count, if count zero, change MJE
        continue, this and at the end MJE is the answer, but if there does not exist MJE, then
        you have to verify if the MJE is actually right or not.
        https://www.youtube.com/watch?v=n5QY3x_GNDg
        * */
        int candidate = nums[0];
        int count = 1;
        for(int i=1; i<nums.length; i++){
            if(candidate == nums[i]){
                count++;
            }else{
                count--;
                if(count==0){
                    candidate=nums[i];
                    count=1;
                }
            }
        }
        return candidate;
    }

    public int bitMasking(int[] nums){
        /*algo is
        https://www.youtube.com/watch?v=0s3zqYaDInE
         1 001
         2 010
         3 011
         3 011
         3 011
         so, for each position, find max set bit
           011 ->3
         does not work if it does not exist, so you have to verify
         3 011
         3 011
         7 111
         7 111
           011
        */
        int len = 16;
        int number = 0;
        int n = nums.length;

        for(int i=0; i<len; i++){
            int count = 0;
            for(int num: nums){
                int a = num & (1 << i);
                if(a > 0){
                    count++;
                }
            }
            if(count > n/2){
                number += 1 << i;
            }
        }
        return number;

    }
}
