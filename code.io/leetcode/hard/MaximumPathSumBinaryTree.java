package leetcode.hard;

import leetcode.TreeNode;

/*
124. Binary Tree Maximum Path Sum
Hard
13.2K
620
Companies
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.



Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

* */
public class MaximumPathSumBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
        int max = Integer.MIN_VALUE;
        //this isNot really hard problem. just need left, right update max, and send local to manager
        public int maxPathSum(TreeNode root) {
            dfs(root);
            return max;
        }
        public int dfs(TreeNode root){
            int left = 0;
            if(root.left != null){
                left = Math.max(dfs(root.left), left);
                //what if they return negative, so setting 0
            }
            int right = 0;
            if(root.right != null){
                right = Math.max(dfs(root.right), right);
            }

            max = Math.max(max, left+right+root.val);
            return Math.max(left + root.val, right + root.val);
        }
}
