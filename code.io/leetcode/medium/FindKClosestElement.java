package leetcode.medium;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* 658. Find K Closest Elements
Medium
6.5K
526
Companies
Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b


Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]
*/
public class FindKClosestElement {

    /*
            * Assume we are taking A[i] ~ A[i + k -1].
        We can binary research i
        We compare the distance between x - A[mid] and A[mid + k] - x

        @vincent_gui listed the following cases:
        Assume A[mid] ~ A[mid + k] is sliding window

        case 1: x - A[mid] < A[mid + k] - x, need to move window go left
        -------x----A[mid]-----------------A[mid + k]----------

        case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
        -------A[mid]----x-----------------A[mid + k]----------

        case 3: x - A[mid] > A[mid + k] - x, need to move window go right
        -------A[mid]------------------x---A[mid + k]----------

        case 4: x - A[mid] > A[mid + k] - x, need to move window go right
        -------A[mid]---------------------A[mid + k]----x------

        If x - A[mid] > A[mid + k] - x,
        it means A[mid + 1] ~ A[mid + k] is better than A[mid] ~ A[mid + k - 1],
        and we have mid smaller than the right i.
        So assign left = mid + 1.

        Important
        Note that, you SHOULD NOT compare the absolute value of abs(x - A[mid]) and abs(A[mid + k] - x).
        It fails at cases like A = [1,1,2,2,2,2,2,3,3], x = 3, k = 3

        The problem is interesting and good.
        Unfortunately the test cases is terrible.
        The worst part of Leetcode test cases is that,
        you submit a wrong solution but get accepted.

        You didn't read my post and up-vote carefully,
        then you miss this key point.


        Complexity
        Time O(log(N - K)) to binary research and find result
        Space O(K) to create the returned list.
*/
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int start = 0;
        int end = arr.length-k;
        //here it Is important that end = midInstead of typical mid -1,As we are not doing
        // start <= end(throws exceptions for arrray out of bound)
        // so it can give wrong ans when ans is mid in start mid end
        //
        while(start < end){
            int mid = start + (end - start)/2;
            //it is important to check that if equals then it should move to left, so end = mid;
            if(x - arr[mid] > arr[mid+k]-x){   // x > (arr[mid] + arr[mid+k])/2
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        return Arrays.stream(arr, start, start+k)
                .boxed()
                .collect(Collectors.toList());
    }


    //this is O(n) simple two pointers approach.
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int start = 0;
        int end = arr.length-1;
        while(end - start>=k){
            if(x - arr[start] <= arr[end]-x){   // x < (arr[mid] + arr[mid+k])/2
                end--;
            }else{
                start++;
            }
        }
        return Arrays.stream(arr, start, start+k)
                .boxed()
                .collect(Collectors.toList());
    }


    /*
    *  Solution - III (Binary-Search and 2-Pointers)

In the above approaches, we are not taking any advantage of the fact that input array given
* to us is already sorted. We can use binary search to find the smallest element in arr which
* is greater or equal to x. Let's mark this index as R. Let's mark the index preceding to R as L
* and element at this index will be smaller than x

Now, [L, R] forms our window of elements closest to x. We have to expand this window to fit k elements.
* We will keep picking either arr[L] or arr[R] based on whichever's closer to x and expand our window
* till it contains k elements.

class Solution {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        int n = size(arr), R = lower_bound(begin(arr), end(arr), x) - begin(arr), L = R - 1;
		// expand the [L, R] window till its size becomes equal to k
        while(k--)
            if(R >= n || L >= 0 && x - arr[L] <= arr[R] - x) L--;  // expand from left
            else R++;                                              // expand from right
        return vector<int>(begin(arr) + L + 1, begin(arr) + R);
    }
};
Time Complexity : O(logn + k), We need O(logn) time complexity to find r at the start.
* Then we need another O(k) time to expand our window to fit k elements
Space Complexity : O(1)


    * */

}
