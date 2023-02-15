package leetcode.medium.week2;

import leetcode.TrieLittleOptimized;
import leetcode.TrieNodeLittleOptimized;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
* 139. Word Break
Medium

11766

509

Add to List

Share
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
*/
//this is the fastest solution for word break in trie
class WordBreakWithTrie{

    private TrieLittleOptimized trieLittleOptimized = new TrieLittleOptimized();
    private TrieNodeLittleOptimized root = new TrieNodeLittleOptimized();
    private Set<Integer> set = new HashSet<>();
    public class TrieLittleOptimized {
        public void insert(String word, TrieNodeLittleOptimized node) {
            TrieNodeLittleOptimized current = node;
            for(char c: word.toCharArray()){
                if(current.get(c) == null){
                    current.put(c);
                }
                current = current.get(c);
            }
            current.setEndOfWord();
        }
        public TrieNodeLittleOptimized search(char c, TrieNodeLittleOptimized node) {
            TrieNodeLittleOptimized current = node;
            if(current.get(c) != null){
                current = current.get(c);
            }else{
                return null;
            }
            return current;
        }
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        wordDict.stream().forEach(word -> trieLittleOptimized.insert(word, root));
        return triHelper(s, 0, root);
    }
    public boolean triHelper(String s, int i, TrieNodeLittleOptimized node){
        if(i==s.length()){
            return true;
        }

        if(set.contains(i)){
            return false;
        }
        set.add(i); // this should be here because if true, it will
        //return right away, in the case of false, we must know that
        //this index is visited and it has returned false;
        for(int j=i; j<s.length(); j++){
            node = trieLittleOptimized.search(s.charAt(j),node);
            if(node != null){
                if(node.isEndOfWord() && triHelper(s, j+1, root)){
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }
}
public class WordsBreak {
    Set<String> set = new HashSet<>();
    int[] dp ;
    TrieLittleOptimized trieLittleOptimized = new TrieLittleOptimized();
    public boolean wordBreak(String s, List<String> wordDict) {
        set = wordDict.stream().collect(Collectors.toSet());
        wordDict.stream().forEach(word -> trieLittleOptimized.insert(word));
        dp = new int[s.length()+1];
        //return helper(s,0);
        return dpHelper(s,0);
    }

    public boolean triDPHelper(String s, int i){
        dp[s.length()] = 1;
        for(int j=s.length()-1; j>=0; j--){
                for(int k=j+1; k<=s.length(); k++){
                    String l = s.substring(j,k);
                    if(trieLittleOptimized.search(l) && dp[k]==1){
                        dp[j] = 1;
                        break;
                    }
                }

        }
        if(dp[0]==1){
            return true;
        }else{
            return false;
        }
    }
    public boolean dpHelper(String s, int i){
        dp[s.length()] = 1;
        for(int j=s.length()-1; j>=0; j--){
            for(int k=j+1; k<=s.length(); k++){
                String l = s.substring(j,k);
                if(set.contains(l) && dp[k]==1){
                    dp[j] = 1;
                    break;
                }
            }
        }
        if(dp[0]==1){
            return true;
        }else{
            return false;
        }
    }
    //this is memo
    public boolean helper(String s, int i){
        if(i==s.length()){
            return true;
        }
        if(dp[i] != 0){
            if(dp[i] == 1){
                return true;
            }else{
                return false;
            }
        }
        for(int j=i+1; j<=s.length(); j++){
            String l = s.substring(i,j);
            if(set.contains(l)){
                if(helper(s,j)){
                    dp[i] = 1;
                    return true;
                }
            }
        }
        dp[i] = 2;
        return false;
    }
    public static void main(String[] args){
        System.out.println(new WordBreakWithTrie().wordBreak("leetcode", Arrays.asList("leet","code")));
    }
}

