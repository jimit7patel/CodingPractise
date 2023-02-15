package leetcode.easy.week1;

import java.util.HashMap;

/*
* Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome here.



Example 1:

Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
*/
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        HashMap<Integer,Integer> count = new HashMap<>();
        s.codePoints().forEach(code -> count.put(code,count.getOrDefault(code,0)+1));
        int ans = 0;
        int oddCount = 0;
        for(int c: count.values()){
            if(c%2 ==0){
                ans +=c;
            }else{
                ans += c-1;
                oddCount++;
            }
        }
        if(oddCount>0){
            ans++;
        }
        return ans;
    }
}
