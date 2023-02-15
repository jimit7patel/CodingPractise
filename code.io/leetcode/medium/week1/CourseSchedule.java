package leetcode.medium.week1;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*207. Course Schedule
Medium

10714

421

Add to List

Share
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
*/
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

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
        }

        return numberOfRemaining==0;
    }

    public boolean canFinishUsingDFS(int numCourses, int[][] prerequisites){
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] p: prerequisites){
            List<Integer> t = adjList.getOrDefault(p[1],new ArrayList<>());
            t.add(p[0]);
            adjList.put(p[1],t);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] finishedProcessing = new boolean[numCourses];
        for(int i=0; i<numCourses; i++){
            if(!visited[i]){
                if(dfs(adjList, i, visited, finishedProcessing)){ //check if cycle
                    return false;
                }
            }
        }
        return true;
    }
    //here omkar has used the concept of departure time and arrival time
    //if dept time has not been set that means there is a back edge.
    public boolean dfs(Map<Integer, List<Integer>> adjList, int n, boolean[] visited, boolean[] finishedProcessing){
        visited[n] = true;
        if(adjList.containsKey(n)){
            for(int nei: adjList.get(n)){
                if(!visited[nei]){
                    if(dfs(adjList, nei, visited, finishedProcessing)){
                        return true;
                    }
                }else{
                    if(!finishedProcessing[nei]){
                        return true; //this means back edge to unfinished node,hence cycle
                    }
                }
            }
        }
        finishedProcessing[n] = true;
        return false;
    }
    public static void main(String[] args){
        System.out.println(new CourseSchedule().canFinishUsingDFS(5, new int[][]{{1,4},{2,4},{3,1},{3,2}}));
    }
}
