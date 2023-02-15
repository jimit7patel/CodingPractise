package leetcode.easy.week1;

import java.util.Arrays;
import java.util.Comparator;

/*
* Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... , determine if a person could attend all meetings.

For example,
Given [ [0, 30], [5, 10], [15, 20] ],
return false.
*/
public class MeetingRoom {

    public boolean canAttendMeetings(int[][] intervals){
        Comparator<int[]> cmp = (a, b) -> (a[0] - b[0]);
        /*
        ToIntFunction<int[]> fn = new ToIntFunction<int[]>() {
            @Override
            public int applyAsInt(int[] value) {
                return value[0];
            }
        };
        Comparator<int[]> cmp2 = Comparator.comparingInt(fn);
        ToIntFunction<int[]> fn = (a -> a[0]);
        Comparator<int[]> cmp1 = Comparator.comparingInt(fn);
         */
        Arrays.sort(intervals, cmp);
        for(int i=0; i<intervals.length-1; i++){
            if(intervals[i][1] > intervals[i+1][0]){
                return false;
            }
        }
        return true;


    }
    public static void main(String[] args){
        System.out.println(new MeetingRoom().canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}}));
    }

}
