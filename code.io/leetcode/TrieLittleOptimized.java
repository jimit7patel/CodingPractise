package leetcode;

import leetcode.TrieNodeLittleOptimized;

public class TrieLittleOptimized {
    private TrieNodeLittleOptimized root;
    public TrieLittleOptimized() {
        root = new TrieNodeLittleOptimized();
    }

    public void insert(String word) {
        TrieNodeLittleOptimized current = root;
        for(char c: word.toCharArray()){
            if(current.get(c) == null){
                current.put(c);
            }
            current = current.get(c);
        }
        current.setEndOfWord();
    }

    public TrieNodeLittleOptimized searchPrefix(String word){
        TrieNodeLittleOptimized current = root;
        for(char c: word.toCharArray()){
            if(current.get(c) != null){
                current = current.get(c);
            }else{
                return null;
            }
        }
        return current;
    }
    public boolean search(String word) {
        TrieNodeLittleOptimized t = searchPrefix(word);
        return t != null && t.isEndOfWord();
    }

    public boolean startsWith(String prefix) {
        TrieNodeLittleOptimized t = searchPrefix(prefix);
        return t != null;
    }
}
