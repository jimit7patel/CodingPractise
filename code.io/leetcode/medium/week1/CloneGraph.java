package leetcode.medium.week1;

import leetcode.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*133. Clone Graph
Medium

6434

2711

Add to List

Share
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}


test.Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 */
public class CloneGraph {
    Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        Node newRoot = new Node(node.val);
        dfs(node, newRoot);
        return newRoot;
    }
    public void dfs(Node oldNode, Node newNode){
        visited.put(oldNode,newNode);
        for(Node n: oldNode.neighbors){
            Node t = new Node(n.val);
            if(!visited.containsKey(n)){
                newNode.neighbors.add(t);
                dfs(n,t);
            }else{
                //this else part is gist of the problem, when there is a reference to its parent
                //as it is undirected graph, the new node needs to bring back the parent as well
                newNode.neighbors.add(visited.get(n));
            }
        }
    }
}
