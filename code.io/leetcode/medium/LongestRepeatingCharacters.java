package leetcode.medium;
/*424. Longest Repeating Character Replacement
Medium
6.9K
268
Companies
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 */
public class LongestRepeatingCharacters {
    public int characterReplacement(String s, int k) {
        int start = 0;
        int[] count = new int[26];
        int maxCount = 0;
        int res = 0;
        for(int end = 0; end < s.length(); end++){
            int c = s.charAt(end) - 'A';
            count[c]++;
            maxCount = Math.max(maxCount, count[c]);
            while(start < end && (end - start +1 - maxCount) > k){
                c = s.charAt(start) - 'A';
                count[c]--;
                maxCount = Math.max(maxCount,count[c]);
                start++;
            }
            res = Math.max(res,end-start+1);
        }
        return res;
    }

    //there is another approach with binarySearch
    //select window sizeAnd then check if any string withThat size is valid
    //o(nlogn)
}
