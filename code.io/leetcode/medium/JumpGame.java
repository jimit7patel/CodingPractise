package leetcode.medium;
/*
* 55. Jump Game
Medium
14.8K
763
Companies
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105
*/
public class JumpGame {
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for(int i=0; i< nums.length; i++){
            //if reachable index is less than current index
            //which means noWayTo move forward
            //this will happen incase sink,Where reachable is toThe sink index
            //and it willNot update the reachableSince it is jump to 0
            // and next index wilLbecome greater than this
            if(reachable < i){
                return false;
            }
            //update the reachable index with the max jump from current index
            reachable = Math.max(reachable,i+nums[i]);

            //if reached
            if(reachable>=nums.length-1){
                return true;
            }
        }
        return true;
    }

    /*
    * When I first see this problem, two things pop up in my mind:

        Maybe I can do some sort of DFS, BFS (with backtracking?) but there will be a lot of redundancies
        Then this begs for Dynamic Programming!
        But my gut feeling was saying that this problem has to have a simpler approach.

        So, here is my thinking process:

        Base case: last index can trivially reach to last index.
        Q1: How can I reach to the last index (I will call it last_position) from a preceding index?
        If I have a preceding index idx in nums which has jump count jump which satisfies idx+jump >= last_position,
        * I know that this idx is good enough to be treated as the last index because all I need to do now is
        * to get to that idx. I am going to treat this new idx as a new last_position.
        I ask Q1 again.
        So now, here are two important things:

        If we have indices which are like sinkholes, those with 0 as jump and every other preceding index can
        * only jump to that sinkhole, our last_position will not be updated anymore because
        * idx+jump >= last_position will not be satisfied at that sinkhole and every other preceding index
        * cannot satisfy the idx+jump >= last_position condition since their jumps are not big enough.
        E.g. nums=[3,2,1,0,4] # Here 0 is a sinkhole becuase all preceding indices can only jump to the sinkhole
        If we have barriers, those indices with 0 as jump, but the preceding indices contain jumps which can go
        * beyond those barriers, idx+jump >= last_position will be satisfied and last_position will be updated.
        E.g. nums=[3,2,2,0,4] # Here 0 is just a barrier since the index before that 0 can jump *over* that barrier
        Finally ask this question when we have finished looping

        Is the last position index of 0? (i.e, have we reached to the beginning while doing the process of
        * jumping and updating the last_position?)
        If we have sinkholes in nums, our last_position will not be 0. Thus, False will be retured.
        That's all!

        This is what I have in mind when I was thinking of this approach :D
        image

        Python
        1. class Solution:
        2.    def canJump(self, nums: List[int]) -> bool:
        3.        last_position = len(nums)-1
        4.
        5.        for i in range(len(nums)-2,-1,-1): # Iterate backwards from second to last item until
        * the first item
        6.            if (i + nums[i]) >= last_position: # If this index has jump count which can reach
        * to or beyond the last position
        7.                last_position = i # Since we just need to reach to this new index
        8.        return last_position == 0
        */
}
