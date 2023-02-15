package leetcode.medium;

import java.util.HashMap;

/*
* 560. Subarray Sum Equals K
Medium
16.6K
490
Companies
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2


Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
*/
public class SubarraySumEqualsK {

    private HashMap<Integer, Integer> hm = new HashMap<>();
    private int[] sum_array;

    public int subarraySum(int[] nums, int k) {

        return optimized(nums,k);
    }
    public int optimized(int[] nums, int k){
        int ans = 0;
        int s = 0;
        hm.put(0,1);
        for(int n: nums){
            s+= n;
            int c = hm.getOrDefault(s-k, 0);
            ans+=c;
            hm.put(s, hm.getOrDefault(s,0)+1);
        }
        return ans;
    }
    public int sumRange(int i, int j) {
        return sum_array[j+1] - sum_array[i];
    }
    public int bruteForce(int[] nums, int k){
        sum_array = new int[nums.length+1];
        sum_array[0] = 0;
        int count = 0;
        for(int i=1; i<nums.length+1; i++){
            sum_array[i] = sum_array[i-1] + nums[i-1];
        }

        for(int i=0; i<nums.length; i++){
            for(int j=i; j<nums.length; j++){
                int ans = sumRange(i,j);
                if(ans==k){
                    count++;
                }
            }
        }
        return count;
    }
}
