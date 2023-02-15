package leetcode.medium.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.



Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
*/
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i< nums.length; i++){
            int a = nums[i];
            if(i>0 && nums[i-1] == a ){  //to remove dups
                continue;
            }
            int j=i+1;
            int k = nums.length-1;
            int target = -a;
            while(j<k){
                int b = nums[j];
                if(nums[j-1] == b & j > i+1){  //to remove dups
                    j++;
                    continue;
                }
                int c = nums[k];
                if(b + c == target){//found pair
                    result.add(Arrays.asList(a,b,c));
                    j++;
                    k--;
                }else if(b+c > target){
                    k--;
                }else{
                    j++;
                }
            }
        }
        return result;
    }
    public List<List<Integer>> threeSumLittleConcise(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i< nums.length; i++){
            int a = nums[i];
            if(i>0 && nums[i-1] == a ){ // this is to remove dups, can use set
                continue;
            }
            int j=i+1;
            int k = nums.length-1;
            int target = -a;
            while(j<k){
                int b = nums[j];
                int c = nums[k];
                if(b + c == target){//found pair
                    result.add(Arrays.asList(a,b,c));
                    while(j < k && nums[j] == nums[j+1]){j++;}  //remove dups
                    while(j < k && nums[k] == nums[k-1]){k--;}  //remove dups
                    j++;k--;
                }else if(b+c > target){
                    k--;
                }else{
                    j++;
                }
            }
        }
        return result;
    }
    public static void main(String[] args){
        List<List<Integer>> out = new ThreeSum().threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6});
        System.out.println(out);
    }
}
