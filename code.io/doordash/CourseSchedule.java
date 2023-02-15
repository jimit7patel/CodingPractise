package doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/*
210. Course Schedule II
        Medium

        6511

        235

        Add to List

        Share
        There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

        For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
        Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.



        Example 1:

        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: [0,1]
        Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
        Example 2:

        Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
        Output: [0,2,1,3]
        Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
        So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
        Example 3:

        Input: numCourses = 1, prerequisites = []
        Output: [0]
*/
public class CourseSchedule {
        private  List<Integer>[] adjList;
        private boolean[] visited;
        int timestamp =0;
        int[] dest;
        int[] result;
        int index;
    public int[] findOrder(int numCourse, int[][] prerequisites){
        adjList = new ArrayList[numCourse];
        visited = new boolean[numCourse];
        dest = new int[numCourse];
        result = new int[numCourse];
        index = numCourse-1;
        for(int i=0; i< adjList.length; i++){
            Supplier<List<Integer>> s = ArrayList::new;
            adjList[i] = s.get();
        }

        for(int i=0; i<prerequisites.length; i++){
            int src = prerequisites[i][0];
            int des = prerequisites[i][1];
            adjList[des].add(src);
        }

        for(int i=0; i<numCourse; i++){
            if(!visited[i]){
                if(dfs(i)){
                    return new int[0];
                }
            }
        }
        return result;
    }

    public boolean dfs(int i){
        visited[i] = true;
        timestamp++;

        for(int n: adjList[i]){
            if(!visited[n]){
                if(dfs(n)){
                  return true;
                }
            }else{

                if(dest[n] == 0){
                    //cycle is detected
                    return true;
                }
            }
        }
        dest[i]=timestamp++;
        result[index--]=i;
        return false;
    }

    public static void main(String[] args){

        Arrays.stream(new CourseSchedule().findOrder(5, new int[][]{{1,0},{2,1},{3,2},{3,4}})).forEach(System.out::println);
    }
}
