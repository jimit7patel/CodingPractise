package leetcode.medium.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.
Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

* https://leetcode.com/problems/insert-interval/
*/
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<List<Integer>> result = new ArrayList<>();
        int i =0;
        while(i < intervals.length && newInterval[0] > intervals[i][1]){
            result.add(Arrays.asList(intervals[i][0],intervals[i][1]));
            i++;
        }
        int a = newInterval[1], b= newInterval[1];
        List<Integer> newInt = new ArrayList<>();
        while(i < intervals.length && newInterval[1] >= intervals[i][0]){
            //merge
            a = Math.min(a, intervals[i][0]);
            b = Math.max(b, intervals[i][1]);
            i++;
        }
        result.add(Arrays.asList(a, b));
        while(i < intervals.length){
            result.add(Arrays.asList(intervals[i][0],intervals[i][1]));
            i++;
        }
        return result.stream()
                .map(interval -> interval.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
    }
    public static void main(String[] args){
        Arrays.stream(new InsertInterval().insert(new int[][]{{1,2},{3,5},{6,7}, {8,10}, {12,16}}, new int[]{4,8}))
                .forEach(a -> System.out.println(a[0]+","+a[1]));


    }
}
