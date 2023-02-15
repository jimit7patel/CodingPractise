package leetcode.medium.week2;
/*
* 5. Longest Palindromic Substring
Medium

20730

1189

Add to List

Share
Given a string s, return the longest palindromic substring in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
*/
public class LongestPalindromicSubstring {
    //most optimal solution O(n2) and constant space.
    //2n-1 centers
    public String longestPalindrome(String s) {
        String res = "";
        int ans = 0;
        for(int i=0; i<s.length(); i++){
            int b = expandAroundCenter(s,i,i+1);
            int a = expandAroundCenter(s,i,i);
            int l = Math.max(a,b);

            if(ans<l){
                ans=l;
                res = s.substring(i-(l-1)/2,(i+l/2)+1);
            }

        }

        return res;
    }

    public int expandAroundCenter(String s, int l, int r){

        while(l>=0 && r<=s.length()-1 && s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }

    public String longestPalindromeUsingDP(String s){
        // get length of input string
        int n = s.length();
        // table[i][j] will be false if
        // substring str[i..j] is not palindrome.
        // Else table[i][j] will be true
        boolean table[][] = new boolean[n][n];
        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for (int i = 0; i < n; ++i)
            table[i][i] = true;
        // check for sub-string of length 2.
        int start = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }
        // Check for lengths greater than 2.
        // k is length of substring
        for (int k = 3; k <= n; ++k) {
            // Fix the starting index
            for (int i = 0; i < n - k + 1; ++i) {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;
                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (table[i + 1][j - 1]
                        && s.charAt(i) == s.charAt(j)) {
                    table[i][j] = true;
                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    //approach 3 LCS of s and reverser of s - this can be done using dp, suffix trie.

}

