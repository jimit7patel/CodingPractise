package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.List;

/*438. Find All Anagrams in a String
Medium

8502

269

Add to List

Share
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
*/
public class FindAllAnagrams {
    double hashcode = 1;
    int[] map = new int[]{2,3,5,7,11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    public List<Integer> findAnagrams(String s, String p){
            int[] frq = new int[26];
            List<Integer> result = new ArrayList<>();
            for(int i=0; i<p.length(); i++){
                frq[p.charAt(i)-'a']++;
            }
            int cnt = 0;
            int start = 0;
            for(int end=0; end<s.length(); end++){ //expanding from left
                if(frq[s.charAt(end)-'a']-- > 0){
                    cnt++;
                }
                while(cnt == p.length()){ //shrinking window from left
                    if(end == start + p.length()-1){
                        result.add(start);
                    }
                    if(frq[s.charAt(start++) - 'a']++ >= 0){
                        //>=0 is important because ==0
                        //aabaa aa this will not decrease cnt and can product incorrect match
                        cnt--;
                    }
                }
            }
            return result;
    }

    // this approach will not work as the multiplication for longer length will not fit
    public List<Integer> findAnagramsUsingHashcode(String s, String p) {
        List<Integer> result = new ArrayList<>();
        for (char c : p.toCharArray()) {
            hashcode *= map[c - 'a'];
        }
        int hashcodeSub = 1;
        for (int i = 0; i < s.length(); i++) {
            hashcodeSub *= map[ s.charAt(i) - 'a'];
            if (i >= p.length()) {
                hashcodeSub = (hashcodeSub / map[s.charAt(i - p.length()) - 'a']);
            }
            if (i >= p.length() - 1) {
                if (hashcode == hashcodeSub) {
                    result.add(i-(p.length()-1));
                }
            }


        }
        return result;
    }

    public static void main(String[] args){
        new FindAllAnagrams().findAnagrams("cbaebabacd", "abc");
        //new FindAllAnagrams().findAnagrams("cbaebabacd", "yqrbgjdwtcaxzsnifvhmou");
    }
}
