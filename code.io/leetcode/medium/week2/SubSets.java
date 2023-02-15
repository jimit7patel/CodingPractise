package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.List;

/*
* 78. Subsets
Medium

11468

168

Add to List

Share
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
*/
public class SubSets {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        helper(nums, 0, new ArrayList<>());
        return res;
    }
    public void helper(int[] nums, int i, List<Integer> out){
        if(i == nums.length){
            res.add(new ArrayList<>(out));
            return;
        }


        out.add(nums[i]);
        helper(nums, i+1, out);
        out.remove(out.size()-1);
        helper(nums, i+1, out);

    }
    public void anotherHelper(int[] nums, int i, List<Integer> cur){
        res.add(new ArrayList<>(cur));
        for(int j=i; j<nums.length; j++){
            cur.add(nums[j]);
            anotherHelper(nums,j+1,cur);
            cur.remove(cur.size()-1);
        }

    }
}
