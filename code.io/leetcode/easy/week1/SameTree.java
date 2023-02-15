package leetcode.easy.week1;

import leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/*
* Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.



Example 1:


Input: p = [1,2,3], q = [1,2,3]
Output: true*/
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }
        if(p == null || q==null){
            return false;
        }
        return dfs(p, q);
        //return bfs(p,q);
    }
    public boolean dfs(TreeNode p, TreeNode q){
        if(p.val != q.val){
            return false;
        }
        boolean ret = true;
        if(p.left != null && q.left != null){
            ret &= dfs(p.left, q.left);
        }else if(p.left != null || q.left != null){
            return false;
        }
        //if left is false, there is no need to go to right but, to use generic template
        //it is traversing but this can be optimized further
        if(p.right != null && q.right != null){
            ret &= dfs(p.right, q.right);
        }else if(p.right != null || q.right != null){
            return false;
        }
        return ret;
    }
    public boolean bfs(TreeNode p, TreeNode q){
        Deque<TreeNode[]> queue = new LinkedList<>();
        queue.addFirst(new TreeNode[]{p,q});
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i< size; i++){
                TreeNode[] t = queue.removeFirst();
                TreeNode t1 = t[0];
                TreeNode t2 = t[1];
                if(t1.val != t2.val){
                    return false;
                }
                if(t1.left != null && t2.left != null){
                    queue.addLast(new TreeNode[]{t1.left,t2.left});
                }else if(t1.left != null || t2.left != null){
                    return false;
                }
                if(t1.right != null && t2.right != null){
                    queue.addLast(new TreeNode[]{t1.right,t2.right});
                }else if(t1.right != null || t2.right != null){
                    return false;
                }
            }
        }
        return true;
    }
}
