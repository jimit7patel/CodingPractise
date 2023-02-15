package leetcode.medium.week2;
/*
* 287. Find the Duplicate Number
Medium

16777

2250

Add to List

Share
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.



Example 1:

Input: nums = [1,3,4,2,2]
Output: 2
Example 2:

Input: nums = [3,1,3,4,2]
Output: 3
All the integers in nums appear only once except for precisely one integer which appears two or more times.
*/
public class findDuplicateNumber {
    //the trick in this problem is that, use value of array as index for next
    //iteration and if there is a duplicate value, you will visit same index twice
    //so either use cycle detection like fast or slow pointer, use marking to
    //know it was visited twice, use array as hashmap to detect that
    public int findDuplicateUsingNegativeMarking(int[] nums){
        for(int i=0; i<nums.length; i++){
            int next = Math.abs(nums[i]);
            if(nums[next] < 0 ){
                return next;
            }
            nums[next] *= -1;
        }
        return -1;
    }

    public int findDuplicateUsingCycleDetection(int[] nums){
        int fast = nums[0];
        int slow = nums[0];

        while(true){
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(fast == slow){
                break;
            }
        }
        slow = nums[0];

        while(slow != fast){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    public int usingArrayAsHashMap(int[] nums){
        int a = 0;
        int next = 0;
        while(nums[next] != a){
            next = nums[next];
            nums[a] = a;
            a = next;
        }
        return a;
    }
}
