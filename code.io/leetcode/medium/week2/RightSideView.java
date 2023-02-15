package leetcode.medium.week2;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*199. Binary Tree Right Side View
Medium

8149

471

Add to List

Share
Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.



Example 1:


Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
*/
public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        if(root == null){
            return res;
        }
        q.addLast(root);
        while(!q.isEmpty()){
            int size = q.size();
            int t =0;
            for(int i=0; i< size; i++){
                TreeNode n = q.removeFirst();
                t = n.val;
                if(n.left != null){
                    q.addLast(n.left);
                }
                if(n.right != null){
                    q.addLast(n.right);
                }
            }
            res.add(t);
        }
        return res;
    }
}
