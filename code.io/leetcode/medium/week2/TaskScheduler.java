package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*621. Task Scheduler
Medium

7074

1379

Add to List

Share
Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.



Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation:
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
Example 2:

Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.
*/
public class TaskScheduler {
    /*Intuition of the problem
        We have to find least number of intervals to finish all tasks. Given that, each task requires one interval, for tasks of size t, we need at least t intervals to complete it.

        Now the problem is reduced to finding the minimum idle time when no task is executing.
        Our result would be idle Time + t (size of task array)

        How to find the idle time ?
        Assume, there 3 A tasks. Tasks = ["A","A","A","B"] . cooling time n = 2 .
        If A Is run at a particular time, we cannot run it for 2 intervals after that.
        So we have to try filling up those 2 spaces with some other task.

         What are the maximum number of idle spaces that we could have?
        It would be (max Frequency task - 1 * n)
        In this example, A has maximum frequency of 3, so there are 2 set of intervals(each of size n=2) that needs to be filled with some other task.
        image

        Once we know the maximum number of idle spaces, we have to just reduce the the count of spaces every time we find a task that can fill up that idle space.

        Steps -
        1) Create count array to keep track of frequency of each task. Size 26 as given in input.
        2) Sort the frequency is ascending order, the task at last position (arr[25]) would be the one with maximum frequency.
        Example -
        Tasks ["A","A","A","B","B","C"]
        A has max occurrence = 3, n = 2. Hence we can place A as follows
        image

        Max idle spaces = (3-1) * 2 = 4. We could see there 4 idle spaces above.

        3)Now we have to just find that there are sufficient tasks to fill these 4 idle spaces.
        We iterate over rest of array in descending order and substract it's count from idle spaces.
        Task B has count 2 and C has count 1. Hence 1 space remains idle.

        image

        Answer , task length + idle time = 6 + 1 = 7

*/
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for(char c: tasks){
            map[c-'A']++;
        }
        Arrays.sort(map);
        //the idea is to reduce the idle time
        int max = map[25] -1;//because intervals are 1 less
        int idle_time = max * n; //idle slots
        for(int i=24; i>=0; i--){
            idle_time -= Math.min(max,map[i]); // this is because if there are frequency as max like AAABBB n=2, AB_AB_AB
            //so, in this case, B will occupy (3-1) = 2, slots not 3
            if(map[i]==0){
                break;
            }

        }
        //if intervals are negative, AAABBBCCC n=1;
        if(idle_time <0){
            idle_time=0;
        }
        return tasks.length + idle_time;
    }
    /*
    * We always want the machine do task with higher frequence first. If we let machine do lower ones, then after machine finish all lower frequence tasks,
    * we have to put idle between higher frequence tasks, which result in higher number of intervals.

If we look closer to the task schedule, we can find out a pattern that each period is of length (n+1). For example, tasks = [A A A A B B B B C C C D] and n = 3,
* the schedule should be:
[A _ _ _ A _ _ _ A _ _ _ A ...].

Whenever there are tasks not finished, machine should start a new work period with length (n+1). The reason we have period of length (n+1) is to prevent
* two same tasks have intervals shorter than n.

So the idea to finish tasks is: if there are tasks not finished, start a new period. In this period, get the highest frequence task from all remaining tasks,
* and put it to the start of this period. Then get the second highest task and put to this period until this period ends or there is no task available.
* If there is no task avalable before this period ends, we have to let the machine in idle state. After each period ends, we try to put tasks back to task pool.
* If there is no task in task pool, we finish all tasks.

So we (1) Sort tasks by frequency in descending order. (2) While task pool has tasks, start a new word period. (3) Get highest frequnce task from task pool to
* fill the slot of this period untill period ends or no more task avalable (4) Put unfinished task back to task pool (5) If task pool has no task, job done.

*/
    public int leastIntervalUsingPQ(char[] tasks, int n){
        if (tasks.length == 0 || n == 0) return tasks.length;

        Map<Character, Integer> taskCount = new HashMap<>(); /* key: task, value: amount of this task remained */
        for (Character t : tasks) {
            taskCount.put(t, taskCount.getOrDefault(t, 0) + 1);
        }

        PriorityQueue< Integer> taskPool = new PriorityQueue<>((a,b)->b-a);

        for (Map.Entry<Character, Integer> entry : taskCount.entrySet()) {
            taskPool.add(entry.getValue());
        }

        int intervals = 0;
        while (!taskPool.isEmpty()) {
            /* start a new work period */
            List< Integer> tasksInPeriod = new ArrayList<>();
            int period = n + 1;
            while (period > 0 && !taskPool.isEmpty()) {
                int currTask = taskPool.poll();
                tasksInPeriod.add(currTask-1);
                intervals++;
                period--;
            }

            /* add remaining tasks to taskPool */
            for (int remainedTask : tasksInPeriod) {
                if (remainedTask > 0) taskPool.add(remainedTask);
            }

            /* check if job done */
            if (taskPool.isEmpty()) break;
            else {
                /* check if idle slots needed */
                if (period > 0) intervals += period;
            }
        }
        return intervals;
    }
}
