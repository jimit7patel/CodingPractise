package leetcode.easy.week1;

import java.util.HashMap;

/*
* Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        HashMap<Integer,Integer> map = new HashMap<>();
        s.codePoints().forEach(code -> map.put(code, map.getOrDefault(code, 0) + 1));
        t.chars().forEach(code -> map.put(code, map.getOrDefault(code, 2) - 1));
        //for(int c: s.toCharArray()){
        //}
        //for(char c: s.toCharArray()){
        //    int index = c - 'a';
        //}

        for(int count: map.values()){
            if(count > 0){
                return false;
            }
        }
        return true;
    }

    public static void main (String[] args){
        new ValidAnagram().isAnagram("rat", "car");
    }
}
