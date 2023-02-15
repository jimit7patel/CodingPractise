package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
* 179. Largest Number
Medium
6.2K
517
Companies
Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.



Example 1:

Input: nums = [10,2]
Output: "210"
Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330"
*/
public class LargestNumber {
    public String largestNumber(int[] nums) {

        Comparator<String> cmp = (b, a) -> (a+b).compareTo(b+a);
        String[] stringNums = new String[nums.length];
        IntStream.range(0,nums.length)
                .forEach(i ->{
                    stringNums[i] = String.valueOf(nums[i]);
                });
        Arrays.sort(stringNums, cmp);
        return stringNums[0].equals("0")?"0":Arrays.stream(stringNums).reduce((s1, s2) -> s1+s2).get();

    }

    public static void main(String[] args){
        System.out.println(new LargestNumber().largestNumber(new int[]{1,2,4}));
    }
}
