package leetcode.medium;
/*
* 91. Decode Ways
Medium
9.3K
4.2K
Companies
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.



Example 1:

Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
*/
public class DecodeWays {
    int[] memo;
    public int numDecodings(String s) {
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(0)!='0'?1:0;
        //dp[2] = Integer.valueOf(s.substring(0,1)) < 26? 2: dp[0];

        for(int i=2; i<=s.length(); i++){
            String t = s.substring(i-2,i);
            int two = Integer.valueOf(t);
            if((s.charAt(i-1)) != '0'){
                dp[i] = dp[i-1];
            }
            if(two >=10 && two <= 26){
                dp[i] += dp[i-2];
            }
        }
        return dp[s.length()];
    }
    public int helper(String s, int i){
        if(i<0){
            return 1;
        }
        if(i==0 && s.charAt(i)!='0'){
            return 1;
        }
        if(memo[i] != 0){
            return memo[i];
        }
        int ans = 0;

        if(s.charAt(i)!='0'){
            ans+=helper(s, i-1);
        }

        if(i-1>=0){
            String t = s.substring(i-1,i+1);
            int two = Integer.valueOf(t);
            if(two >=10 && two <= 26){
                ans+=helper(s,i-2);
            }
        }
        memo[i] = ans;
        return ans;
    }

}
