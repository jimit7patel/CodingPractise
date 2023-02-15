package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/longest-increasing-subsequence/solutions/1326308/c-python-dp-binary-search-bit-segment-tree-solutions-picture-explain-o-nlogn/

public class LongestIncreasingSubsequence {


    //using DP
    //this is classical DP problem.

    public int lengthOfLIS(int[] nums) {
        int ans = 1;
        int[]dp = new int[nums.length];
        Arrays.fill(dp,1);
        for(int i=1; i<nums.length; i++){
            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int usingBinarySearch(int[] nums){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i=1; i<nums.length; i++){
            if(nums[i] > list.get(list.size()-1)){
                list.add(nums[i]);
            }else{
                int index = binarySearch(list, nums[i]);
                list.set(index, nums[i]);
            }
        }
        return list.size();
    }

    public int binarySearch(List<Integer> list, int n){
        int start = 0;
        int end = list.size()-1;

        while(start < end){
            int mid = start + (end-start)/2;
            if(list.get(mid) == n){
                return mid;
            }
            if(list.get(mid) > n){
                end = end -1;
            }else{
                start = start + 1;
            }
        }
        return  start;
    }

    public static void main(String[] args){
        new LongestIncreasingSubsequence().usingBinarySearch(new int[]{0,1,0,3,2,3});
    }
}
