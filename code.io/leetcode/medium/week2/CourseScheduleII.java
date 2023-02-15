package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
* 210. Course Schedule II
Medium

7548

255

Add to List

Share
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
*/
public class CourseScheduleII {
    int[] visited;
    List<Integer> res = new ArrayList<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] p: prerequisites){
            List<Integer> t = adjList.getOrDefault(p[1],new ArrayList<>());
            t.add(p[0]);
            adjList.put(p[1],t);
        }
        for(int i=0; i<numCourses; i++){
            if(visited[i] == 0) {
                if(dfs(adjList, i)){
                    return new int[0];
                }
            }
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public boolean dfs(Map<Integer, List<Integer>> adjList, int i){
        visited[i] = 1;
        if (adjList.containsKey(i)) {
            for (int n : adjList.get(i)) {
                if(visited[n]==0){
                    if(dfs(adjList, n)){
                        return true;
                    }
                }
                else if(visited[n] ==1){
                    //cycle
                    return true;
                }
            }
        }
        visited[i] = 2;
        res.add(0,i);
        return false;
    }
    public int[] usingIndegrees(int numCourses, int[][] prerequisites){
        int[] indegrees = new int[numCourses];
        int numberOfRemaining = numCourses;
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] p: prerequisites){
            indegrees[p[0]]++;
            List<Integer> t = adjList.getOrDefault(p[1],new ArrayList<>());
            t.add(p[0]);
            adjList.put(p[1],t);
        }
        Deque<Integer> q = new LinkedList<>();
        for(int i=0; i< indegrees.length; i++){
            if(indegrees[i] == 0){
                q.addLast(i);
                numberOfRemaining--;
            }
        }
        while(!q.isEmpty()){
            int n = q.removeFirst();
            if(adjList.containsKey(n)) {
                for (int l : adjList.get(n)) {
                    indegrees[l]--;
                    if (indegrees[l] == 0) {
                        numberOfRemaining--;
                        q.addLast(l);
                    }
                }
            }
            res.add(n);
        }
        return numberOfRemaining==0?res.stream().mapToInt(i -> i).toArray():new int[0];
    }
}
