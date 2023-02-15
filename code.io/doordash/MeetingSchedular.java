package doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

/*
* Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6
* */
public class MeetingSchedular {

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {

        Comparator<int[]> cmp = Comparator.comparingInt(a -> a[0]);
        Comparator<int[]> comparator = (a, b) -> a[0] - b[0];
        Arrays.sort(slots1, cmp);
        Arrays.sort(slots2, cmp);

        int i = 0;
        int j = 0;

        while(i <  slots1.length && j < slots2.length){
            int[] a = slots1[i];
            int[] b = slots2[j];

            if(a[0] < b[0]){
                if(a[1] > b[0] && a[1] - b[0] >= duration){ //found answer
                    return Arrays.asList(b[0], b[0]+ duration);
                }
                i++;
            }else{
                if( b[1] > a[0] && b[1] - a[0] >= duration){ //found answer
                    return Arrays.asList(a[0], a[0]+ duration);
                }
                j++;
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args){
        int[][] slot1 = {{10,50},{60,120},{140,210}};
        int[][] slot2 = {{0,15},{60,70}};
        int duration = 8;
        new MeetingSchedular().minAvailableDuration(slot1,slot2,duration)
                .stream()
                .forEach(System.out::println);
    }
}
