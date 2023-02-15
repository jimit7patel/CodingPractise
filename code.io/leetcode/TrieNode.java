package leetcode;

import java.util.HashMap;

public class TrieNode {
    public HashMap<Character,TrieNode> children;

    public boolean endOfWord;

    public TrieNode(){

        children = new HashMap<>();

        endOfWord = false;

    }

}
