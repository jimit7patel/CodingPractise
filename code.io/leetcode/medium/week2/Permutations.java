package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* 46. Permutations
Medium

12224

210

Add to List

Share
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
*/
public class Permutations {
    List<List<Integer>> arrayList = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        helper(nums, 0, new ArrayList<>());
        return arrayList;
    }
    public void helper(int[] nums, int i, List<Integer> res){
        if(i==nums.length){
            //arrayList.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            arrayList.add(new ArrayList<>(res));
            return;
        }
        for(int j=i; j<nums.length; j++){
            res.add(nums[j]);
            swap(nums, j, i);
            helper(nums, i+1, res);
            swap(nums, i, j);
            res.remove(res.size()-1);
        }
    }
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    public static void main(String[] args){
        List<List<Integer>> out = new Permutations().permute(new int[]{1,2,3});
    }
}
