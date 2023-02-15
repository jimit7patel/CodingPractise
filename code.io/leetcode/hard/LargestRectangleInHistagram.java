package leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

/*
* 84. Largest Rectangle in Histogram
Hard
13K
184
Companies
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.



Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
*/
public class LargestRectangleInHistagram {
    //the idea is if it is increasing order, keep pushingAnd when it is low, pop and calculate
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        stack.addLast(-1);
        int area = Integer.MIN_VALUE;
        for(int i=0; i<heights.length; i++){
            while(stack.getFirst()!=-1 && heights[stack.getFirst()]>=heights[i]){
                int currentHeight = heights[stack.removeFirst()];
                int currentWidth = i - stack.getFirst() - 1;
                int l = currentHeight * currentWidth;
                area = Math.max(area, l);
            }
            stack.addFirst(i);
        }
        while(stack.getFirst()!=-1){
            int currentHeight = heights[stack.removeFirst()];
            int currentWidth = heights.length - stack.getFirst() -1;
            int l = currentHeight * currentWidth;
            area = Math.max(area, l);
        }
        return area;
    }
}
