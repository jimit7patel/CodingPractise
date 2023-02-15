package leetcode.medium.week1;

import java.util.HashSet;
import java.util.Set;

/*
* 3. Longest Substring Without Repeating Characters
Medium

27281

1176

Add to List

Share
Given a string s, find the length of the longest substring without repeating characters.
Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

* */
public class LongestSubStringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int one = 0;
        int ans = 0;
        Set<Integer> map = new HashSet<>();
        for(int second =0; second < s.length(); second++){
            int c = s.charAt(second);
            while(map.contains(c) && one <= second){//until window becomes valid and shrink window
                int index = s.charAt(one++);
                map.remove(index);
            }
            map.add(c);
            ans = Math.max(ans, second - one + 1);
        }
        return ans;
    }

    public int approach2LittleMoreOptimized(String s){
        int[] map = new int[128];
        int ans = 0;
        for(int second =0, one=0; second < s.length(); second++){
            int c = s.charAt(second);
            /*
             * this approach is also sliding window but it stores (index+1) of seen character
             * so that it directly skips to index after repeated character
             * and it may be possible that repetition exists because we do not delete map of characters out of
             * window but the can be found because those will be older index than current so using math.max
             * */
            one = Math.max(one,map[c]);
            map[c] = second+1;
            ans = Math.max(ans, second - one + 1);
        }
        return ans;
    }

    public static void main(String[] args){
        System.out.println(new LongestSubStringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
    }
}
