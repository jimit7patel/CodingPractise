package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
* Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
*/
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Comparator<int[]> cmp = (a,b) -> a[0] - b[0];
        Arrays.sort(intervals,cmp);
        List<int[]> result = new ArrayList<>();
        for(int[] a: intervals){
            if(result.size()==0 || a[0] > result.get(result.size()-1)[1]) {
                result.add(a);
            }
           else{
                int[] t = result.get(result.size()-1);
                t[1] = Math.max(t[1],a[1]);
                result.set(result.size()-1, t);
            }
        }
        return result.toArray(new int[result.size()][2]);
    }
    public static void main(String[] args){
        int[][] res = new MergeIntervals().merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        System.out.println(res);
    }
}
