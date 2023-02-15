package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.List;

/*
* 39. Combination Sum
Medium

12678

261

Add to List

Share
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
*/
public class CombinationSum {
    List<List<Integer>> arrayList = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        helper(candidates, target, 0, new ArrayList<>());
        return arrayList;
    }
    public void helper(int[] candidates, int target, int i, List<Integer> res){
        if(target < 0){
            return;
        }
        if(target == 0){
            arrayList.add(new ArrayList<>(res));
            return;
        }
        if(i+1 < candidates.length) {
            helper(candidates, target, i + 1, res);
        }
        res.add(candidates[i]);
        helper(candidates,target-candidates[i],i,res);
        res.remove(res.size()-1);
    }
    //below is another helper not very intuitive
    public void anotherhelper(int[] candidates, int target, int i, List<Integer> t){
        if(target==0){
            arrayList.add(new ArrayList<>(t));
            return;
        }
        if(target>0){
            for(int j=i; j<candidates.length;j++){
                t.add(candidates[j]);
                anotherhelper(candidates,target-candidates[j],j,t);
                t.remove(t.size()-1);
            }
        }
    }
    public static void main(String[] args){
        List<List<Integer>> res = new CombinationSum().combinationSum(new int[]{2,3,6,7}, 7);
        res.size();
    }
}

