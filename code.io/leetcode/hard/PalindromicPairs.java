package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 336. Palindrome Pairs
Hard
4.1K
426
Companies
You are given a 0-indexed array of unique strings words.

A palindrome pair is a pair of integers (i, j) such that:

0 <= i, j < word.length,
i != j, and
words[i] + words[j] (the concatenation of the two strings) is a
palindrome
.
Return an array of all the palindrome pairs of words.



Example 1:

Input: words = ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["abcddcba","dcbaabcd","slls","llssssll"]
Example 2:

Input: words = ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]
Example 3:

Input: words = ["a",""]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["a","a"]


Constraints:

1 <= words.length <= 5000
0 <= words[i].length <= 300
words[i] consists of lowercase English letters.
*/
class PalindromicPairs {

    private static class TrieNode {
        TrieNode[] next;
        List<Integer> list;
        int index;
        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        TrieNode root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(words, i, root, res);
        }

        return res;
    }

    private void addWord(TrieNode root, String word, int index) {

        //add reverse and also
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';

            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }

            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }

            root = root.next[j];
        }

        root.list.add(index);
        root.index = index;//this indicates end of word
        //list is used for mataining ispalindrome in sub string.
        //we can not only use list because then we do not have a way of
        //determing end of word
    }

    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            //if end of word in trie, and is palindrome rest of the word, case (a,b,x)(a,b)
            if (root.index >= 0 && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }

        //case (a,b) (x,a,b)
        //iterate through all possible x
        //except same this to detect self palindrome like (a,b,a)
        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i <= j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }
}
