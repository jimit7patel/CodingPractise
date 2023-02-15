package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoggleSolver {
    static class TrieNode {
        // stores presence of current node in trie
        int cnt;
        // marks true if current node is end of word in trie
        boolean isEnd;
        // stores references to all its children
        HashMap<Character,TrieNode> child;
        // paramtrized constructor
        TrieNode(){
            child=new HashMap<>();
            isEnd=false;
            cnt=1;
        }
    };
    static TrieNode root = new TrieNode();
    static List<String> result = new ArrayList<>();

// inserts key "k" in the trie
        static void insert(String k) {
            TrieNode tmp = root;
            int l = k.length();
            for (int i = 0; i < l; i++) {
                TrieNode n;
                char c = k.charAt(i);
                if (!tmp.child.containsKey(c)) {
                    n = new TrieNode();
                }
                else {
                    n = tmp.child.get(c);
                    n.cnt++;
                }
                tmp.child.put(c,n);
                tmp = n;
            }
            tmp.isEnd = true;
        }
        static void removeKey(String s) {
            int l = s.length();
            TrieNode cur = root;
            for (int i = 0; i < l; i++) {
                char c = s.charAt(i);
                if (cur==null || !cur.child.containsKey(c)) {
                    return;
                }
                else {
                    TrieNode t = cur.child.get(c);
                    t.cnt--;
                    if(t.cnt==0){
                        cur.child.remove(c);
                    }
                    cur=t; //this can become null
                }
            }
        }
        static boolean ok(int x, int y, List<String> mat, TrieNode rt) {
            // mat dimension
            int n = mat.size();
            int m = mat.get(0).length();
            // basic boundary checks
            if (x < 0 || y < 0 || x >= n || y >= m) {
                return false;
            }
            // basic sanity check
            if (rt == null) {
                return false;
            }
            // checking if mat[x][y] exists as child for rt
            if (!rt.child.containsKey(mat.get(x).charAt(y))) {
                return false;
            }
            return true;
        }
        static void dfs(int x, int y, List<String> mat, int[][] vis, TrieNode rt, StringBuilder res) {
            // check if rt node is end of any word in trie
            if (rt!=null && rt.isEnd == true) {
                // unmark the end node
                rt.isEnd = false;
                // push the current word in the result array
                result.add(res.toString());
                // remove the current word from the trie
                removeKey(res.toString());
            }
            // mark current node as visited
            vis[x][y] = 1;
            // iterate in all 8 - directions from current cell
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    // if this cell(x+i,y+j) is valid and not visited
                    if (ok(x + i, y + j, mat, rt) && (vis[x + i][y + j]!=1)) {
                        // building the word further
                        res.append(mat.get(x+i).charAt(y+j));
                        // extend dfs traversal to cell(x+i,y+j)
                        dfs(x + i, y + j, mat, vis, rt.child.get(mat.get(x+i).charAt(y+j)), res);
                        // pop current char from the end of current word
                        res.deleteCharAt(res.length()-1);

                        //res.delete(i,j)
                    }
                }
            }
            // mark current cell as non - visited
            vis[x][y] = 0;
        }
        public static List<String> boggle_solver(List<String> dictionary, List<String> mat) {
            for (String word : dictionary) {
                insert(word);
            }
            int n = mat.size();
            int m = mat.get(0).length();
            int[][] vis = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (root.child.containsKey(mat.get(i).charAt(j))) {
                        StringBuilder word = new StringBuilder();
                        word.append(mat.get(i).charAt(j));
                        dfs(i, j, mat, vis, root.child.get(mat.get(i).charAt(j)), word);
                    }
                }
            }
            return result;
        }
}
