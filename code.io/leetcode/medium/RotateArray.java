package leetcode.medium;
/*
* 189. Rotate Array
Medium
12.4K
1.5K
Companies
Given an array, rotate the array to the right by k steps, where k is non-negative.



Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]


Constraints:

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105


Follow up:

Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
Could you do it in-place with O(1) extra space?
*/
public class RotateArray {
        public void rotate(int[] nums, int k) {
            if(nums.length <= 1){
                return;
            }
            //step each time to move
            int step = k % nums.length;
            reverse(nums,0,nums.length - 1);
            reverse(nums,0,step - 1);
            reverse(nums,step,nums.length - 1);
        }

        //reverse int array from n to m
        //thi is using xor transitive properties
        public void reverse(int[] nums, int n, int m){
            while(n < m){
                nums[n] ^= nums[m];
                nums[m] ^= nums[n];
                nums[n] ^= nums[m];
                n++;
                m--;
            }
        }

        //using extra space.
    public void rotate2(int[] nums, int k) {
        if(nums.length <= 1){
            return;
        }
        //step each time to move
        int step = k % nums.length;
        int[] tmp = new int[step];
        for(int i = 0; i < step; i++){
            tmp[i] = nums[nums.length - step + i];
        }
        for(int i = nums.length - step - 1; i >= 0; i--){
            nums[i + step] = nums[i];
        }
        for(int i = 0; i < step; i++){
            nums[i] = tmp[i];
        }

    }

    //this one is TLE because O(n * K) algo
    public void rotate3(int[] nums, int k){
        int step = k % nums.length;
        for(int i=0; i<step; i++){
            int prev = nums[nums.length-1];
            for(int j=0; j<nums.length; j++){
                int tmp = nums[j];
                nums[j] = prev;
                prev = tmp;
            }
        }

    }

}
