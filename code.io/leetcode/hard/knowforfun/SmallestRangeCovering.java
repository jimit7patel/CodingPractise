package leetcode.hard.knowforfun;
/*
* 632. Smallest Range Covering Elements from K Lists
Hard
2.8K
47
Companies
You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.



Example 1:

Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation:
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Example 2:

Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]*/

//understand this from solution here through slides
//https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/solutions/127459/smallest-range/
//same as merge kList but keep trackOf max in each iteration.
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCovering {
    private record ElementWithIndex(int e, int i, int index){};

    public int[] smallestRange(List<List<Integer>> nums){
        PriorityQueue<ElementWithIndex> pq = new PriorityQueue<>((a,b) -> a.e - b.e);
        int k = nums.size();
        int max = Integer.MIN_VALUE;
        int range = Integer.MAX_VALUE;
        int start = -1, end = -1;
        for(int i =0; i<k; i++){
            pq.add(new ElementWithIndex(nums.get(i).get(0), i, 0));
            max = Math.max(max, nums.get(i).get(0));
        }

        while(pq.size()==nums.size()){
            ElementWithIndex t = pq.poll();
            if(max - t.e < range){
                range = max - t.e;
                start = t.e;
                end = max;
            }
            int index = t.index;
            index++;
            if(index<nums.get(t.i).size()){
                int e = nums.get(t.i).get(index);
                pq.add(new ElementWithIndex(e, t.i, index));
                max = Math.max(max, e);
            }
        }
        return new int[]{start, end};
    }
    public static void main(String[] args){
        new SmallestRangeCovering().smallestRange(Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,2,3), Arrays.asList(1,2,3)));
    }
}
