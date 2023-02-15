package leetcode.medium.week1;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
* 102. Binary Tree Level Order Traversal
Medium

10100

195

Add to List

Share
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
*/
public class BinaryTreeLevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> out = new ArrayList<>();
        if(root == null ){
            return out;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0; i< size; i++){
                TreeNode t= q.removeFirst();
                level.add(t.val);
                if(t.left!= null){
                    q.addLast(t.left);
                }
                if(t.right != null){
                    q.addLast(t.right);
                }
            }
            out.add(level);
        }
        return out;
    }
}
