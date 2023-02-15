package leetcode;

import java.util.HashMap;

public class TrieNodeLittleOptimized {
    private static final int SIZE = 26;
    private TrieNodeLittleOptimized[] children;

    private boolean endOfWord;

    public TrieNodeLittleOptimized(){

        children = new TrieNodeLittleOptimized[SIZE];

        endOfWord = false;

    }
    public TrieNodeLittleOptimized get(char c){
        return children[c-'a'];
    }
    public void put(char c){
        children[c-'a'] = new TrieNodeLittleOptimized();
    }

    public boolean isEndOfWord(){
        return endOfWord;
    }

    public void setEndOfWord(){
        endOfWord = true;
    }
}
