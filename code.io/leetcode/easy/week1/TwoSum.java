package leetcode.easy.week1;

import java.util.HashMap;

/*
* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

*/
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i< nums.length; i++){
            int lookup = target - nums[i];
            if(map.containsKey(lookup)){
                return new int[]{i, map.get(lookup)};
            }
            map.put(nums[i], i);
        }

        return new int[]{};

    }

}
