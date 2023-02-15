package leetcode.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
892 · Alien Dictionary
* There is a new alien language which uses the latin alphabet. However, the order among letters are
* unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted
* lexicographically by the rules of this new language. Derive the order of letters in this language.



You may assume all letters are in lowercase.
The dictionary is invalid, if string a is prefix of string b and b is appear before a.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return the smallest in normal lexicographical order.
The letters in one string are of the same rank by default and are sorted in Human dictionary order.
Example
Example 1:

Input：["wrt","wrf","er","ett","rftt"]
Output："wertf"
Explanation：
from "wrt"and"wrf" ,we can get 't'<'f'
from "wrt"and"er" ,we can get 'w'<'e'
from "er"and"ett" ,we can get 'r'<'t'
from "ett"and"rftt" ,we can get 'e'<'r'
So return "wertf"*/
public class AlienDictionary {
    private HashMap<Character, Set<Character>> adj = new HashMap<>();
    private HashSet<Character> set = new HashSet<>();
    private HashMap<Character, Integer> visited = new HashMap<>();
    StringBuilder result = new StringBuilder();
    int timestamp =1;
    public void buildAdjList(String[] words){
        for(int i=0; i< words.length; i++){
            int l = Math.min(words[i].length(),words[i+1].length());
                for(int k=0; k<l; k++){
                    if(words[i].charAt(k) != words[i+1].charAt(k)){
                        Set<Character> s = adj.getOrDefault(words[i].charAt(k), new HashSet<>());
                        s.add(words[i+1].charAt(k));
                        adj.put(words[i].charAt(k), s);
                        break;
                    }
                }
            }
    }
    public boolean dfs(Character c){
        visited.put(c,1);
        //timestamp++;
        if(adj.containsKey(c)){
            for(char d: adj.get(c)){
                if(!visited.containsKey(d)){
                    if(dfs(d)){
                        return true;
                    }
                }else{
                    if(visited.get(d) ==1){
                        return true;
                    }
                }
            }
        }
        visited.put(c, 2);
        result = result.append(c);
        return false;
    }
    public String findOder(String[] words){
        for(String s : words){
            for(Character c: s.toCharArray()){
                set.add(c);
            }
        }
        buildAdjList(words);
        for(Character c: set){
            if(!visited.containsKey(c)){
                if(dfs(c)){return "";}
            }
        }
        return result.reverse().toString();


    }

}
