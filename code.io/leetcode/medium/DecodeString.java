package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
* 394. Decode String
Medium
10K
443
Companies
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
*/
public class DecodeString {
//theProblem is simple but error prune toCode
    public String decodeString(String s) {
        Deque<Integer> count = new LinkedList<>();
        Deque<String> letter = new LinkedList<>();
        letter.addFirst("");
        int i = 0;
        while(i<s.length()){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                //StringBuilder sb = new StringBuilder();
                int start = i;
                while(Character.isDigit(s.charAt(i))){
                    //sb.append(s.charAt(i)-'0');
                    i++;
                }
                count.addFirst(Integer.parseInt(s.substring(start,i)));
                i--;
            }else if (c == '['){
                //add until number or bracket comes
                StringBuilder sb = new StringBuilder();
                while(Character.isLetter(s.charAt(++i))){
                    sb.append(s.charAt(i));
                }
                i--;
                letter.addFirst(sb.toString());
            }else if(c ==']'){
                //closing bracket
                StringBuilder sb = new StringBuilder();
                String t = letter.removeFirst();
                int times = count.removeFirst();
                for (int j = 0; j < times; j++) {
                    sb.append(t);
                }
                //if (letter.isEmpty()) {
                //    letter.addFirst(sb.toString());
                //} else {
                letter.addFirst(letter.removeFirst() + sb.toString());
                //}
            }else{
                //if (letter.isEmpty()) {
                //    letter.addFirst(String.valueOf(c));
                //} else {
                letter.addFirst(letter.removeFirst() + String.valueOf(c));
                //}
            }
            i++;
        }
        return letter.removeFirst();
    }

    public static void main(String[] args){
        System.out.println(new DecodeString().decodeString("100[leetcode]"));
    }

}
