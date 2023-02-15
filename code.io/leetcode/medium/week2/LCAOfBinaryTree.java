package leetcode.medium.week2;

import leetcode.TreeNode;

/*
* 236. Lowest Common Ancestor of a Binary Tree
Medium

11874

299

Add to List

Share
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”



Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
*/
public class LCAOfBinaryTree {
    TreeNode lca;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return lca;
    }
    boolean[] helper(TreeNode root, TreeNode p, TreeNode q){
        boolean pFound = false;
        boolean qFound = false;
        if(root == p){
            pFound = true;
        }
        if(root == q){
            qFound = true;
        }

        //pass leaf code
        boolean[] left = new boolean[2];
        boolean[] right = new boolean[2];
        if(root.left != null){
            left = helper(root.left, p, q);
        }
        if(root.right != null){
            right = helper(root.right, p, q);
        }
        pFound = pFound || left[0] || right[0];
        qFound = qFound || left[1] || right[1];

        if(pFound && qFound && lca == null){
            lca = root;
        }
        return new boolean[]{pFound,qFound};
    }
}
/*

int alternate(Node head, Node a, Node b){

        populateParent(head,null);

        int la = findheight(a,head,0);

        int lb = findheight(b,head,0);

        int diff = Math.abs(la-lb);



        if(la>lb){

        Node tmp = b;

        b = a;

        a = tmp;

        }

        while(diff-->0){

        b = parent.get(b);

        }

        while(a!=b){

        a = parent.get(a);

        b = parent.get(b);

        }

        return a.data;

        }

        void populateParent(Node root, Node prev){

        parent.put(root,prev);

        if(root.left != null){

        populateParent(root.left,root);

        }

        if(root.right!=null){

        populateParent(root.right,root);

        }

        }



        int findheight(Node cur, Node root, int l){



        if(cur == root){

        return l;

        }

        return findheight(parent.get(cur),root,l+1);



        }


*/
