package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/*
* 435. Non-overlapping Intervals
Medium
5.2K
150
Companies
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
*/
public class NonOverlappingIntervals {
    //I have solved this with sorting from start element but the actual solutionIs
    //sorting basedOn end element
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)  return 0;

        Comparator<int[]> cmp = (a, b) -> a[1] - b[1];
        Arrays.sort(intervals, cmp);
        int end = intervals[0][1];
        int ans = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0]>=end){
                end = intervals[i][1];
            }else{
                ans++;
            }
        }
        return ans;
    }
    public int eraseOverlapIntervalsBasedOnStart(int[][] intervals) {
        if (intervals.length == 0)  return 0;

        Comparator<int[]> cmp = (a, b) -> a[0] - b[0];
        Arrays.sort(intervals, cmp);
        int end = intervals[0][1];
        int ans = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0]<end){
                ans++;
                end = Math.min(end, intervals[i][1]);
            }else{
                end = intervals[i][1];
            }
        }
        return ans;
    }
}
