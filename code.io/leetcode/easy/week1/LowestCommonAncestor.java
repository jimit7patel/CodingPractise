package leetcode.easy.week1;

import leetcode.TreeNode;

/*
* Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
*/


/*
this is for binary tree.

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





public class LowestCommonAncestor {
    TreeNode lca = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > Math.min(p.val, q.val) && root.val < Math.max(p.val, q.val)){
            return root;
        }
        if(root.left != null && root.val > Math.max(p.val, q.val)){
            return lowestCommonAncestor(root.left, p, q);
        }
        if(root.right != null && root.val < Math.min(p.val, q.val)){
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
        //helper(root, p, q);
        //return lca;
    }

    /*
    This is for binary tree. Binary search is way simpler
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

     */
    public static void main(String[] args){
        TreeNode root = new TreeNode(2);
        TreeNode a = new TreeNode(1);
        root.left = a;

        System.out.println(new LowestCommonAncestor().lowestCommonAncestor(root, root, a).val);
    }
}
