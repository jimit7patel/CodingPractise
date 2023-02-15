package doordash;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.

        A connected trio is a set of three nodes where there is an edge between every pair of them.

        The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.

        Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.



        Example 1:


        Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
        Output: 3
        Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure above.
        Example 2:


        Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
        Output: 0
        Explanation: There are exactly three trios:
        1) [1,4,3] with degree 0.
        2) [2,5,6] with degree 2.
        3) [5,6,7] with degree 2.


        Constraints:

        2 <= n <= 400
        edges[i].length == 2
        1 <= edges.length <= n * (n-1) / 2
        1 <= ui, vi <= n
        ui != vi
        There are no repeated edges.
 */
public class MinTrioDegree {
    record Trio(int x, int y, int z){}
    HashSet<Integer>[] adjList;
    int[] totalDegree;
    public int minTrioDegree(int n, int[][] edges){

        adjList = new HashSet[n+1];
        totalDegree = new int[n+1];
        int ans = Integer.MAX_VALUE;

        for(int i=0; i< edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            if(adjList[u] == null){
                adjList[u] = new HashSet<>();
            }
            if(adjList[v] == null){
                adjList[v] = new HashSet<>();
            }
            adjList[u].add(v);
            adjList[v].add(u);
            totalDegree[u]++;
            totalDegree[v]++;
        }


       return min(n);

    }

    /*
    *
    * The above solution returns TLE may be because of sorting and duplicate check
    * in order to avoid, one solution is to start from node 1 so we do not have
    * to check for duplicate
    * */

    public int min(int n){
         /*
        Set<Trio> visited = new HashSet<>();
       for(int i=0; i< edges.length; i++){
           int u = edges[i][0];
           int v = edges[i][1];
           for(int node: adjList[u]){
                if(!visited.contains(sorted(u,v,node)) && adjList[v].contains(node)){//found trio
                    ans = Math.min(ans, totalDegree[u] + totalDegree[v] + totalDegree[node] - 6);
                    visited.add(sorted(u,v,node));
                }
           }
       }
        */
        int ans = Integer.MAX_VALUE;
        for(int u=1; u<=n; u++){
            if(adjList[u] != null){ //not all nodes have edges
                for(int v: adjList[u]){
                    if(v > u){
                        for(int w: adjList[v]){
                            if(w > v){
                                if(adjList[u].contains(w)) {
                                    //found trio
                                    ans = Math.min(ans, totalDegree[u] + totalDegree[v] + totalDegree[w] - 6);
                                }
                            }
                        }
                    }
                }
            }

        }
        return ans == Integer.MAX_VALUE?-1:ans;
    }
    /*
    * Below is finding it through adjMatrix, otherwise similar approach.
    * */
    public int minTrioDegree2(int n, int[][] edges) {
        int[][] graph = new int[401][401];
        int[] cnt = new int[n + 1];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
            cnt[edge[0]]++;
            cnt[edge[1]]++;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (graph[i][j] == 0) continue;
                for (int k = j + 1; k <= n; k++) {
                    if (graph[i][j] == 1 && graph[i][k] == 1 && graph[j][k] == 1) {
                        int local = cnt[i] + cnt[j] + cnt[k] - 6;
                        if (local == 0) return 0;
                        if (local < res) res = local;
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    Trio sorted (int x, int y, int z){
        int[] a = new int[]{x,y,z};
        Arrays.sort(a);
        return new Trio(a[0],a[1],a[2]);
    }

    public static void main (String[] args){
        System.out.println(new MinTrioDegree().minTrioDegree(6, new int[][]{{1,2},{1,3},{3,2},{4,1},{5,2},{3,6}}));
    }
}