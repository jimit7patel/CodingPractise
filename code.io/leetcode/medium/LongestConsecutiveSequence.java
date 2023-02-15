package leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
* 128. Longest Consecutive Sequence
Medium
14.3K
585
Companies
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.



Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
*/
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums){
        Set<Integer> set = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());
        int ans = 0;

        for(int n: nums){
            int l =1;
            if(!set.contains(n-1)){ //this is critical toAvoid dups
                n++;
                while(set.contains(n)){
                    n++;
                    l++;
                }
            }
            ans = Math.max(ans, l);
        }
        return ans;
    }
}
