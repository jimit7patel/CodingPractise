package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
* Meeting Rooms II
Medium
Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei),
find the minimum number of conference rooms required.
Example 1:
Input:
[[0, 30],[5, 10],[15, 20]]
Output:
 2
Example 2:
Input:
 [[7,10],[2,4]]

Output:
 1
*/
public class MeetingRoomII {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing((int[] itv) -> itv[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count = 0;
        for (int[] itv : intervals) {
            if (heap.isEmpty()) {
                count++;
                heap.offer(itv[1]);
            } else {
                if (itv[0] >= heap.peek()) {
                    heap.poll();
                } else {
                    count++;
                }

                heap.offer(itv[1]);
            }
        }

        return count;
    }
}
