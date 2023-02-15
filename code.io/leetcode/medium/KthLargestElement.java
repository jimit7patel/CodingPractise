package leetcode.medium;
/*215. Kth Largest Element in an Array
Medium
12.8K
642
Companies
Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

You must solve it in O(n) time complexity.



Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
*/
public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length-1;
        k = nums.length-k;
        while(start <= end){
            int p = lomutos(nums, start, end);
            if(p == k){
                return nums[k];
            }else if(p < k){
                start=p+1;
            }else {
                end=p-1;
            }
        }
        return -1;
    }

    public int lomutos(int[] nums, int start, int end){
        int pivot = nums[start];
        int orange = start;
        int green = end;
        while(orange <= green){
            if(nums[orange] <= pivot){
                orange++;
            }else{
                swap(nums,orange,green);
                green--;
            }
        }
        swap(nums,start,green);
        return green;
    }
    public void swap(int a[], int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public static void main(String[] args){
        System.out.println(new KthLargestElement().findKthLargest(new int[]{3,2,1,5,6,4}, 5));
    }
}
