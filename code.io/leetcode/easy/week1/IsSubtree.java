package leetcode.easy.week1;

import leetcode.TreeNode;

/*
* 572. Subtree of Another Tree
Easy

5810

316

Add to List

Share
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

*/
public class IsSubtree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        boolean ans = false;
        if(root.val == subRoot.val){
            ans = isSameTree(root, subRoot);
        }
        if(root.left != null){
            ans |= isSubtree(root.left, subRoot);
        }
        if(root.right != null){
            ans |= isSubtree(root.right, subRoot);
        }

        return ans;
    }

    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p.val != q.val){
            return false;
        }
        boolean ret = true;
        if(p.left != null && q.left != null){
            ret &= isSameTree(p.left, q.left);
        }else if(p.left != null || q.left != null){
            return false;
        }
        //if left is false, there is no need to go to right but, to use generic template
        //it is traversing but this can be optimized further
        if(p.right != null && q.right != null){
            ret &= isSameTree(p.right, q.right);
        }else if(p.right != null || q.right != null){
            return false;
        }
        return ret;
    }

}
