package doordash;
/*
* A dasher sometimes travels between cities. To avoid delays, the dasher first checks for the shortest routes.
* Given a map of the cities and their bidirectional roads represented by a graph of nodes and edges, determine which given roads are along any shortest path.
* Return an array of strings, one for each road in order, where the value is YES if the road is along a shortest path or NO if it is not.
* The roads or edges are named using their 1-based index within the input arrays.

Example
given a map of g_nodes = 5 nodes, the starting nodes, ending nodes and road lengths are:

Road from/to/weight
1 (1, 2, 1)
2 (2, 3, 1)
3 (3, 4, 1)
4 (4, 5, 1)
5 (5, 1, 3)
6 (1, 3, 2)
7 (5, 3, 1)

Example Input: (5, [1, 2, 3, 4, 5, 1, 5], [
2, 3, 4, 5, 1, 3, 3], [1, 1, 1, 1, 3, 2, 1])
The traveller must travel from city 1 to city g_nodes, so from node 1 to node 5 in this case.
The shortest path is 3 units long and there are three paths of that length: 1 → 5, 1 → 2 → 3 → 5, and 1 → 3 → 5.
Return an array of strings, one for each road in order, where the value is YES if a road is along a shortest path or NO if it is not.
* In this case, the resulting array is ['YES', 'YES', 'NO', 'NO', 'YES', 'YES', 'YES']. The third and fourth roads connect nodes (3, 4) and (4, 5) respectively.
* They are not on a shortest path, i.e. one with a length of 3 in this case.

The solution seems to be dijestra or bellmon ford
* */
public class ShortestPath {
}
