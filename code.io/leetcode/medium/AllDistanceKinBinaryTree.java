package leetcode.medium;

import leetcode.TreeNode;

import java.util.*;

/*
* 863. All Nodes Distance K in Binary Tree
Medium
7.9K
156
Companies
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:

Input: root = [1], target = 1, k = 3
Output: []
*/
public class AllDistanceKinBinaryTree {
    private HashMap<TreeNode, TreeNode> parents = new HashMap<>();
    //algo is simple, first populate parent in map then do levelOrder traversal
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        populateParent(root, null);

        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(target);
        int l = 0;
        List<Integer> ans = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                TreeNode t = q.removeFirst();
                if(l==k){
                    ans.add(t.val);
                }else{
                    if(t.left != null && !visited.contains(t.left.val)){
                        q.addLast(t.left);
                        visited.add(t.left.val);
                    }
                    if(t.right != null && !visited.contains(t.right.val)){
                        q.addLast(t.right);
                        visited.add(t.right.val);
                    }
                    if(parents.containsKey(t) && parents.get(t)!= null && !visited.contains(parents.get(t).val)){
                        q.addLast(parents.get(t));
                        visited.add(parents.get(t).val);
                    }
                }
            }
            l++;
        }
        return ans;

    }

    public void populateParent(TreeNode root, TreeNode pre){
        parents.put(root, pre);
        if(root.left != null){
            populateParent(root.left, root);
        }
        if(root.right != null){
            populateParent(root.right, root);
        }
    }
}
