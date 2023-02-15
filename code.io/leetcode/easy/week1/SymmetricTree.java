package leetcode.easy.week1;

import leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/*
* Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
Input: root = [1,2,2,3,4,4,3]
Output: true

 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {

        Deque<TreeNode[]> queue = new LinkedList<>();
        queue.addLast(new TreeNode[]{root,root});

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode[] t = queue.removeFirst();
                if(t[0].val != t[1].val){
                    return false;
                }
                if(t[0].left != null && t[1].right != null){
                    queue.addLast(new TreeNode[]{t[0].left, t[1].right});
                }else if(t[0].left != null || t[1].right != null){
                    return false;
                }
                if(t[0].right != null && t[1].left != null){
                    queue.addLast(new TreeNode[]{t[0].right, t[1].left});
                } else if(t[0].right != null || t[1].left != null){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode one = new TreeNode(2);
        root.left = one;
        root.right = one;

        TreeNode two =new TreeNode(3);
        root.left.left = two;
        TreeNode four = new TreeNode(4);
        root.left.right = four;
        root.right.left = four;
        root.right.right = two;

        System.out.println(new SymmetricTree().isSymmetric(root));


    }
}
