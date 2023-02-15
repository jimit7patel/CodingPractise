package leetcode.easy.week1;

import leetcode.TreeNode;

/*
* Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.



*/
public class DiameterOfBinaryTree {
    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return ans;
    }
    public int helper(TreeNode root){
        if(root.left == null && root.right == null){
            return 0;
        }
        int lh = 0;
        int rh = 0;
        int height = 0;
        if(root.left != null){
            lh = helper(root.left)+1;
        }
        if(root.right != null){
            rh = helper(root.right)+1;
        }
        height = lh + rh;
        ans = Math.max(ans, height);
        return Math.max(lh,rh);
    }
}
