package leetcode.hard;

import java.util.HashMap;

/*
* 76. Minimum Window Substring
Hard
13.9K
606
Companies
Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?

*/
public class MinimumWindowSubstring {

    //standard dynamic sliding window problem.
    //use count of character in both map, to compare, use another count to check how many of
    //t are matched in s. once allFrom t areMatched, that count isValid window
    //use standard dynamic window template.
    //a bit complex to implement,Algo is simple
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> set = new HashMap<>();
        for(int i=0; i<t.length(); i++){
            set.put(t.charAt(i),set.getOrDefault(t.charAt(i),0)+1);
        }

        HashMap<Character,Integer> map = new HashMap<>();
        int res = s.length()+1;
        int sIndex = 0;
        int eIndex = 0;
        int count = 0;
        int start = 0;

        for(int end=0; end<s.length(); end++){
            Character c = s.charAt(end);
            if(set.containsKey(c)){
                map.put(c,map.getOrDefault(c,0)+1);
                if(map.get(c).intValue()==set.get(c).intValue()){
                    count++;
                }
                while(start<=end && count==set.size()){
                    Character d = s.charAt(start);
                    if(end-start+1<res){
                        res = end-start+1;
                        sIndex = start;
                        eIndex = end;
                    }
                    if(map.containsKey(d)){
                        map.put(d,map.get(d)-1);
                        if(map.get(d)<set.get(d)){
                            count--;
                        }
                    }
                    start++;
                }
            }
        }
        return res==s.length()+1?"":s.substring(sIndex,eIndex+1);
    }
}
