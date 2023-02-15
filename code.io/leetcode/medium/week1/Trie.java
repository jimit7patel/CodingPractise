package leetcode.medium.week1;
/*
* 208. Implement Trie (Prefix Tree)
Medium

7828

96

Add to List

Share
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True
*/

import leetcode.TrieNode;

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for(char c: word.toCharArray()){
            if(!current.children.containsKey(c)){
                TrieNode n = new TrieNode();
                current.children.put(c,n);
            }
            current = current.children.get(c);
        }
        current.endOfWord=true;
    }

    public TrieNode searchPrefix(String word){
        TrieNode current = root;
        for(char c: word.toCharArray()){
            if(current.children.containsKey(c)){
                current = current.children.get(c);
            }else{
                return null;
            }
        }
        return current;
    }
    public boolean search(String word) {
        TrieNode t = searchPrefix(word);
        return t != null && t.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode t = searchPrefix(prefix);
        return t != null;
    }
}
