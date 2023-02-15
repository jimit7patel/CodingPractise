package leetcode.easy.week1;

import java.util.HashSet;
import java.util.stream.IntStream;

/*
* Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();
        for(int num: nums){
            if(seen.contains(num)){
                return true;
            }
            seen.add(num);
        }
        return false;
        //return nums.length != IntStream.of(nums).distinct().count();
    }
}
