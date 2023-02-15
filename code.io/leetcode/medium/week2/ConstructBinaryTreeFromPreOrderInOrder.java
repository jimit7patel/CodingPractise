package leetcode.medium.week2;

import leetcode.TreeNode;
import com.sun.source.tree.Tree;

import java.util.HashMap;

/*
* 105. Construct Binary Tree from Preorder and Inorder Traversal
Medium

10514

282

Add to List

Share
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]
*/
public class ConstructBinaryTreeFromPreOrderInOrder {
    HashMap<Integer,Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0; i<inorder.length; i++){
            map.put(inorder[i],i);
        }
        return helper(preorder,inorder,0,0,inorder.length-1);
    }

    public TreeNode helper(int[] preorder, int[] inorder, int i, int k, int l){
        if(k > l){
            return null;
        }
        TreeNode n = new TreeNode();
        n.val = preorder[i];
        //map to get index of root from inorder
        int index = map.get(n.val);
        //use size to move forward that much in preorder to get root
        int size = index - k;

        n.left = helper(preorder, inorder, i+1, k, index-1);
        n.right = helper(preorder, inorder, i+1+size, index+1, l);

        return n;
    }
}
