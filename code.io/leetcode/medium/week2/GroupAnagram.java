package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 49. Group Anagrams
Medium

11447

362

Add to List

Share
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
*/
public class GroupAnagram {
    public List<List<String>> groupAnagramWorking(String[] strs){
        Map<String, List<String>> group = new HashMap<>();

        for(String s: strs){
            //char[] map = new char[26];
            int[] map = new int[26];
            for(char c: s.toCharArray()){
                 map[c - 'a']++;
            }
            //String hash = String.valueOf(map); //for this char array is needed,
            String hash = buildMap(map);
            List<String> l = group.getOrDefault(hash, new ArrayList<>());
            l.add(s);
            group.put(hash, l);
        }
        return group.values().stream().toList();
    }
    public String buildMap(int[] map){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<26; i++ ){
            if(map[i] >0){
                sb.append(i+'a').append(map[i]);
            }
        }
        return sb.toString();
    }

    //this soln will not work for long string as hascode can go out of integer range
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] map = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,53,59,61,67,71,73,79,83,89,97,101,103};
        Map<Integer, List<String>> group = new HashMap<>();
        for(String s: strs){
            int hash = 1;
            for(char c: s.toCharArray()){
               hash *= map[c - 'a'];
            }
            List<String> l = group.getOrDefault(hash, new ArrayList<>());
            l.add(s);
            group.put(hash, l);
        }
        return group.values().stream().toList();
    }
    public static void main(String[] args){
        new GroupAnagram().groupAnagramWorking(new String[]{"eat","tea","tan","ate","nat","bat"});
    }
}
