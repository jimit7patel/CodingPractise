package leetcode.easy.week1;

import java.util.Arrays;

/*
* Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".



Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
*/
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        return binarySearch_approach4(strs);
    }
    public String approach1(String[] strs){
        String prefix = strs[0];
        for(int i=1; i< strs.length; i++){
            while(strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty()){
                    return "";
                }
            }
        }
        return prefix;
    }

    public String approach2(String[] strs){
        StringBuilder result = new StringBuilder();
        for(int i=0; i<strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i>=strs[j].length() || strs[j].charAt(i) != c) {
                    return result.toString();
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    public String approach3(String[] strs){
        if(strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs);
        int i=0;
        while(i<strs[0].length() && strs[0].charAt(i) == strs[strs.length-1].charAt(i)){
            i++;
        }
        return strs[0].substring(0,i);
    }
    public String binarySearch_approach4(String[] strs){
        int start = 1;
        int end = Arrays.stream(strs).min((a, b) -> a.length() - b.length()).get().length();
        while(start <= end){
            int mid = start + (end - start)/2;
            if(ifCommonPrefix(strs, mid)){
                start = mid +1;
            }else{
                end = mid - 1;
            }
        }
        if(end == -1){
            return "";
        }
        if(end == 0){
            return "";
        }
        return strs[0].substring(0,end);
    }

    public boolean ifCommonPrefix(String[] strs, int l){

        String cp = strs[0].substring(0, l);
        for(int i=1; i<strs.length; i++){
            if(!strs[i].substring(0, l).equals(cp)){
                return false;
            }
        }
        return true;
    }

}
