package leetcode.easy.week1;

import leetcode.TreeNode;

/*
* Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

*/
public class BalancedBinaryTree {
    class IsBalancedWithHeight{
        int height;
        boolean isBalanced;
        IsBalancedWithHeight(int height, boolean isBalanced){
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
    boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return false;
        }
        helper(root);
        return isBalanced;
    }
/*
    public IsBalancedWithHeight helper(TreeNode root){
        if(root.left == null && root.right == null){
            return new IsBalancedWithHeight(1, true);
        }
        IsBalancedWithHeight leftTree = new IsBalancedWithHeight(0, true);
        if(root.left != null){
            leftTree = helper(root.left);
        }
        IsBalancedWithHeight rightTree = new IsBalancedWithHeight(0, true);
        if(root.right != null){
            rightTree = helper(root.right);
        }
        if(leftTree.isBalanced && rightTree.isBalanced && Math.abs(leftTree.height - rightTree.height) <= 1){
            return new IsBalancedWithHeight(Math.max(leftTree.height, rightTree.height) + 1, true);
        }
        else{
            return new IsBalancedWithHeight(0, false);
        }
    }
   */

    public int helper(TreeNode root){

        int lHeight = 0;
        int rHeight = 0;

        if(root.left != null){
          lHeight = 1 + helper(root.left);
        }
        if(root.right != null){
          rHeight = 1 + helper(root.right);
        }
        if(Math.abs(lHeight - rHeight) > 1){
            isBalanced = false;
        }
        return Math.max(lHeight, rHeight);

    }

    public static void main(String[] args){
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);

        a.right = b;
        b.right = c;
        System.out.println(new BalancedBinaryTree().isBalanced(a));
    }
}
