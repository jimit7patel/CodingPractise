package leetcode.easy.week1;
/*
* Given the root of a binary tree, invert the tree, and return its root.



Example 1:


Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
*/
public class InvertBinaryTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        helper(root);
        return root;
    }

    public void helper(TreeNode node){
        if(node.left != null){
            helper(node.left);
        }
        if(node.right != null){
            helper(node.right);
        }
        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;
    }

}
