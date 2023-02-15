package leetcode.medium.week2;
/*
* 31. Next Permutation
Medium

12670

3729

Add to List

Share
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The*/
public class NextPermutation {
    /*explanation:
    from last, find the first which is not in descending order.
    replace previous of that index, to the first one which is greater than previous of index.
    then reverse the index to end.
    for 3 2 1 already descending, just execute reverse part.
    * */
    public void nextPermutation(int[] nums) {
        int i = nums.length-1;
        while(i>0 && nums[i-1] >= nums[i]){
            i--;
        }
        if(i>0){
            int j = nums.length-1;
            while(j>i && nums[j]<=nums[i-1]){
                j--;
            }
            swap(nums,j,i-1);
        }
        reverse(nums, i, nums.length-1);

    }
    public void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    public void reverse(int[] nums, int i, int j){
        while(i<j){
            swap(nums,i,j);
            i++;j--;
        }
    }
}
