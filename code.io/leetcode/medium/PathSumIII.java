package leetcode.medium;

import leetcode.TreeNode;

import java.util.HashMap;

/*
* 437. Path Sum III
Medium
9K
433
Companies
Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).



Example 1:


Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
Example 2:

Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3


Constraints:

The number of nodes in the tree is in the range [0, 1000].
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000
*/
public class PathSumIII {
    int result = 0;
    HashMap<Long, Integer> hm;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null){return 0;};
        hm = new HashMap<>();
        hm.put(0l,1);
        helper(root, 0, targetSum);
        return result;
    }
    public void helper(TreeNode node, long current, int target){
        long total = current+node.val;
        result += hm.getOrDefault(total-target, 0);
        hm.put(total, hm.getOrDefault(total,0)+1);
        if(node.left != null){
            helper(node.left, total, target);
        }
        if(node.right != null){
            helper(node.right, total, target);
        }
        hm.put(total , hm.get(total)-1);
        return;
    }

}
