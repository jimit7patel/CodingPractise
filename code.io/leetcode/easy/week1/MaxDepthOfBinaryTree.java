package leetcode.easy.week1;

import leetcode.TreeNode;

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 */

public class MaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }//this is to avoid nNPE, ideally use helper fn and use rest of code in helper

        if(root.left == null && root.right == null){
            return 1;
        }
        int depth = 0;
        if(root.left != null){
            depth = Math.max(depth, maxDepth(root.left)+1);
        }
        if(root.right != null){
            depth = Math.max(depth, maxDepth(root.right)+1);
        }
        return depth;
    }
}
