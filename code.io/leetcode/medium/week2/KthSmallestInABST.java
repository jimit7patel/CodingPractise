package leetcode.medium.week2;

import leetcode.TreeNode;
import com.sun.source.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;

/*
* 230. Kth Smallest Element in a BST
Medium

8181

142

Add to List

Share
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.



Example 1:


Input: root = [3,1,4,null,2], k = 1
Output: 1
*/
public class KthSmallestInABST {
    private int ans = -1;
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return ans;
    }
    public void helper(TreeNode n, int k){
        if(n.left != null){
            helper(n.left, k);
        }
        if(ans != -1){
            return;
        }
        if(++count == k){
            ans = n.val;
            return;
        }
        if(n.right != null){
            helper(n.right, k);
        }
        return;
    }
    //iterative is not simple, its okay if you skip this
    public int iterative(TreeNode root, int k){
            int c=0;
            Deque<TreeNode> s = new LinkedList<>();
            TreeNode cur = root;
            s.addFirst(root);
            while(cur != null || !s.isEmpty()){
                if(cur!=null){
                    s.addFirst(cur);
                    cur = cur.left;
                }
                else{
                    cur = s.removeFirst();
                    c++;
                    if(c==k){
                        return cur.val;
                    }
                    cur = cur.right;
                }

            }
            return -1;
    }
}
