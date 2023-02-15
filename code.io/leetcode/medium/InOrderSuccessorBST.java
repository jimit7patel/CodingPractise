package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
* Find inorder successor for the given key in a BST
Google Translate Icon
Given a BST, find the inorder successor of a given key in it. If the given key does not lie in the BST,
* then return the next greater node (if any) present in the BST.

An inorder successor of a node in the BST is the next node in the inorder sequence. For example,
* consider the following BST:

*/
public class InOrderSuccessorBST {
    //https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/
    //there are so many approaches but the easier one is dfs inorder

    TreeNode successor;
    public void InorderSuccessor(TreeNode root, TreeNode node){

        if(root.left != null){
            InorderSuccessor(root.left, node);
        }
        if(root.val > node.val && successor== null){
            successor = root;
            return;
        }
        else if(successor != null && root.val > node.val && root.val < successor.val) {
            successor = root;
            return;
        }
        if(root.right != null){
            InorderSuccessor(root.right, node);
        }
    }

    public static TreeNode inOrderSuccessorBFS(TreeNode root, int key)
    {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val > key)
                return root;
            root = root.right;
        }
        return null;
    }

}
