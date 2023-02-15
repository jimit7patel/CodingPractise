package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
113. Path Sum II

* Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.



Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: []
Example 3:

Input: root = [1,2], targetSum = 0
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000
*/
public class PathSum3 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null)
            return res;
        helper(root,new ArrayList<>(),sum);
        return res;
    }

    public void helper(TreeNode c, List<Integer> l, int s){
        if(c.left == null && c.right == null){//leaf node
            if(c.val==s){
                l.add(c.val);
                res.add(new ArrayList<>(l));
                l.remove(l.size()-1);
            }
            return;
        }
        l.add(c.val);
        if(c.left != null){
            helper(c.left,l,s-c.val);
        }
        if(c.right != null){
            helper(c.right,l,s-c.val);
        }
        l.remove(l.size()-1);
    }

}
