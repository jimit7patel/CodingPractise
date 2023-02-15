package leetcode.medium.week1;

import leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/*
* 98. Validate Binary Search Tree
Medium

11268

956

Add to List

Share
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 */
public class ValidateBST{

    boolean ifValid = true;
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        inorder(root);
        return ifValid;
    }
    public void inorder(TreeNode node){
        //boolean ifvalid = true;
        if(node.left != null){
            inorder(node.left);
        }
        if(ifValid && (pre!= null && (node.val <= pre.val))){
            ifValid = false;
            return;
        }
        pre = node;
        if(node.right != null){
            inorder(node.right);
        }
        return;
    }
    public boolean isValidBSTT(TreeNode root) {
        if(root == null){
            return true;
        }
        return usingRange(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }
    public boolean usingRange(TreeNode node, long min, long max){
        if(node.val < min || node.val > max){
            return false;
        }
        boolean ifValid = true;
        if(node.left != null){
            ifValid = usingRange(node.left, min, node.val);
        }
        if(ifValid && node.right != null){
            return usingRange(node.right, node.val, max);
        }
        return ifValid;
    }
    //iterative but it is difficult

    public boolean helperIterative(TreeNode root){

        Deque<TreeNode> s = new LinkedList<>();
        TreeNode cur = root;


        while(cur != null || !s.isEmpty()){
            if(cur!=null){
                s.addFirst(cur);
                cur = cur.left;
            }
            else{
                cur = s.removeFirst();
                if(pre!= null && cur.val <= pre.val){
                    return false;
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return true;
    }
}
