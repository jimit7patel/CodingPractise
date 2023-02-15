package leetcode.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NumberOfConnectedComponents {
    class Solution {

        List<Integer>[] list;

        public int countComponents(int n, int[][] edges) {
            int connected = 0;
            boolean[] visited = new boolean[n];
            list = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                list[i] = new ArrayList<>();
            }
            for (int a[] : edges) {
                list[a[0]].add(a[1]);
                list[a[1]].add(a[0]);
            }
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    //bfs(i,visited);
                    //dfs(i,visited);
                    helper(i, visited);
                    connected++;
                }
            }
            return connected;
        }
        public void bfs(int i,boolean[] visited){
            visited[i] = true;
            Deque<Integer> q = new LinkedList<>();
            q.addLast(i);
            while(!q.isEmpty()){
                int x =  q.removeFirst();
                for(int nei: list[x]){
                    if(!visited[nei]){
                        q.addLast(nei);
                        visited[nei]=true;
                    }
                }
            }
        }
        public void helper(int i, boolean[] visited){
            visited[i] = true;
            for(int nei: list[i]){
                if(!visited[nei]){
                    helper(nei,visited);
                }
            }
        }
    }
}
