package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
* 103. Binary Tree Zigzag Level Order Traversal
Medium
7.8K
206
Companies
Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
*/
public class ZigzagTraversal {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if(root == null){
            return res;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean flip = false;
        while(!q.isEmpty()){
            int k = q.size();
            List<Integer> tmp = new ArrayList<>();
            for(int i=0; i<k; i++){
                TreeNode c = q.pop();
                if(flip){
                    tmp.add(0,c.val);
                }else{
                    tmp.add(c.val);
                }

                if(c.left != null){
                    q.add(c.left);
                }
                if(c.right != null){
                    q.add(c.right);
                }
            }
            if(flip){
                //Collections.reverse(tmp);
                flip = false;
            }else{
                flip = true;
            }
            res.add(tmp);

        }
        return res;
    }

}
