package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static java.util.Collections.singletonList;

/*
* A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.



Example 1:


Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
*/
class MinimumHeightTreesOptimized{
    /*
    Explanation is here https://www.youtube.com/watch?v=ivl6BHJVcB0
    1 - 2 - 3 - 4 - 5 , 3 rooted tree will be min
    1 2 3 4 5 6 , ---3,4 will be ans;
    1 and 6 are leaf so tree from that node will be longest, so lets remove them
    now, 2 and 5 are again leaf so , lets remove them
    ans = [3, 4]
    so, start from indegrees == 1, remove the edges, add those with 1, keep processing until most 2 n's are left.
    * */
    public List<Integer> findMinHeightTrees(int n, int[][] edges){
        if(n == 1){
            return Collections.singletonList(0);
        }
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            adjList.add(i, new ArrayList<>());
        }
        for(int[] edge: edges){
            int a = edge[0];
            int b = edge[1];

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        Deque<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            if(adjList.get(i).size() == 1){  //find out indegrees of 1
                q.addLast(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while(!q.isEmpty()){
            int size = q.size();
            result.clear();
            for(int i=0; i<size; i++ ){
                int a = q.removeFirst();
                if(!adjList.get(a).isEmpty()){
                    int b = adjList.get(a).get(0);
                    adjList.get(b).remove(Integer.valueOf(a));
                    if(adjList.get(b).size() == 1){
                        q.addLast(b);
                    }
                }
                result.add(a);
            }
        }

        return result;

    }
}

public class MinimumHeightTrees {

    //this is very simple but it is TLE
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            adjList.add(i, new ArrayList<>());
        }

        for(int[] edge: edges){
            int a = edge[0];
            int b = edge[1];

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }
        int min = Integer.MAX_VALUE;
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<n; i++){
            int h = height(adjList, i, new HashSet<>());
            if(h < min){
                ans.clear();
                ans.add(i);
                min = h;
            }
            else if(h == min){
                ans.add(i);
            }
        }
        return ans;
    }
    public int height(List<List<Integer>> adjList, int n, Set<Integer> visited){
        int height = 0;
        visited.add(n);
        for(int nei: adjList.get(n)){
            if(!visited.contains(nei)){
                height = Math.max(height, height(adjList, nei, visited));
            }
        }
        return height+1;
    }
}

