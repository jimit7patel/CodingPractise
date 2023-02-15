package leetcode.medium;


import java.util.*;
import java.util.stream.IntStream;

/*
* LeetCode 261. Graph Valid Tree

Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
* */
public class GraphValidTree {
    List<Integer>[] list;

    public boolean ifValidTree(int n, int[][] edges){
        int connected = 0;
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        list = new ArrayList[n];
        IntStream.range(0,n)
                .forEach( i-> list[i] = new ArrayList<>());

        Arrays.stream(edges)
                .forEach( i ->{
                    list[i[0]].add(i[1]);
                    list[i[1]].add(i[0]);
                });

        for(int i=0; i<n; i++){
            if(!visited[i]){
                if(dfs(i, visited, parent)){
                    return false;
                }
            }
            connected++;
        }
        return connected==1;

    }

    public boolean dfs(int i, boolean[] visited, int[] parent){
        visited[i] = true;

        for(int n: list[i]){
            if(!visited[n]){
                parent[n] = i;
                if(dfs(n, visited, parent)){
                    return true;
                }

            }
            else if(parent[i] != n){ //cycle detected
                return true;
            }
        }
        return false;
    }

    public boolean bfs(int i, boolean[] visited, int[]  parent){
        visited[i] = true;
        Deque<Integer> q = new LinkedList<>();
        q.addLast(i);
        while(!q.isEmpty()){
            int k = q.removeFirst();
            for(int n: list[k]){
                if(!visited[n]){
                    parent[n] = i;
                    visited[n] = true;
                    q.addLast(n);
                }else if(parent[i] != n){ //cycle
                    return true;
                }
            }
        }

        return false;
    }

}
