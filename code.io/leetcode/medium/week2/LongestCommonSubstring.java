package leetcode.medium.week2;

import java.util.HashMap;
import java.util.Map;

/*
* https://leetcode.com/discuss/interview-question/1273766/longest-common-substring
* */
public class LongestCommonSubstring {
    public int lcs(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        int idx = 0;
        int ans = 0;
        for(int i=1; i<s1.length(); i++){
            for(int j=1; j<s2.length(); j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                if(dp[i][j] > ans){
                    ans = dp[i][j];
                    idx = i;
                }
            }
        }
        return ans;
        //return s1.substring(idx-max, idx);
    }
}
class Solution {
    /**
     * @param : A string
     * @param : B string
     * @return: the length of the longest common substring.
     */
    static class TrieNode{
        HashMap<Character,TrieNode> children;
        boolean endOfWord;
        TrieNode(){
            children = new HashMap<>();
            endOfWord = false;
        }
    }
    TrieNode root = null;
    public void buildSuffixTree(String s1, String s2){
        root = new TrieNode();
        //for s1 suffix tree
        for(int i=0; i<s1.length(); i++){
            insert(s1.substring(i), '$');
        }
        //for s2
        for(int i=0; i<s2.length(); i++){
            insert(s2.substring(i), '#');
        }
    }
    public void insert(String s, Character ch){
        TrieNode current = root;
        for(int i=0; i<s.length(); i++){
            Character c = s.charAt(i);
            TrieNode n = current.children.get(c);
            if(n==null){
                n = new TrieNode();
                current.children.put(c,n);
            }
            current = n;
        }
        //appending end of sequence;
        //current.endOfWord = true;
        current.children.put(ch,new TrieNode());
    }
    public int[] getLongestCommonSubstring(TrieNode current, Character ch){
        int dollarCount=0;
        int hashCount=0;
        int lcs = 0;
        for(Map.Entry<Character,TrieNode> c: current.children.entrySet()){
            if(c.getKey()=='$'){ //base case
                dollarCount++;
            }else if(c.getKey()=='#'){ //base case
                hashCount++;
            }else{
                int[] r = getLongestCommonSubstring(c.getValue(),c.getKey());
                dollarCount+=r[0];
                hashCount+=r[1];
                lcs=Math.max(lcs,r[2]);
            }
        }
        if(dollarCount>0 && hashCount>0 && ch != '*')
        {
            lcs=lcs+1;
        }
        return new int[]{dollarCount,hashCount,lcs};
    }

    public int longestCommonSubstring(String A, String B) {
        buildSuffixTree(A,B);
        int[] result = getLongestCommonSubstring(root,'*');
        return result[2];
    }
}
