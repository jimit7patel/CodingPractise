package leetcode.medium.week2;

import leetcode.TrieNodeLittleOptimized;

/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.


Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True

*/
public class DesignAddSearchWordsDataStructure {

    class TrieNodeLittleOptimized {
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
    private TrieNodeLittleOptimized root;
    public DesignAddSearchWordsDataStructure() {
        root = new TrieNodeLittleOptimized();
    }

    public void addWord(String word) {
        TrieNodeLittleOptimized current = root;
        for(char c: word.toCharArray()){
            if(current.get(c) == null){
                current.put(c);
            }
            current = current.get(c);
        }
        current.setEndOfWord();
    }
    public boolean search(String word){
        return searchWithIndex(word, 0, root);
    }

    public boolean searchWithIndex(String word, int index, TrieNodeLittleOptimized node) {
        for(int i=index; i<word.length(); i++){
            char c= word.charAt(i);
            if(c == '.'){
                for(int j=0; j<26;j++ ) {
                    if(node.children[j] != null && searchWithIndex(word, i+1, node.children[j])){
                        return true;
                    }
                }
                return false;
            }else {
                if (node.get(c) != null) {
                    node = node.get(c);
                } else {
                    return false;
                }
            }
        }
        return node.isEndOfWord();
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
