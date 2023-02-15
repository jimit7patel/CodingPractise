package leetcode.easy.week1;
/*
* Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.



Example 1:

Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
*/
public class CountingBits {
    public int[] countBits(int num){
        return approach1(num);
        //return approach2(num);
        //return approach3(num);
    }
    public int[] approach1(int num){
        int[] result = new int[num+1];
        for(int i=0; i<=num; i++){
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }
    public int[] approach2(int num){
        int[] result = new int[num+1];
        if(num == 0){
            return result;
        }
        result[0] = 0;
        result[1] = 1;
        int devider = 2;
        for(int i=2; i<= num; i++){
            if(i%devider==0 && i/devider>1){
                devider = devider << 1;
            }
            result[i] = result[i-devider] + 1;
        }
        return result;
    }
    public int[] approach3(int num){
        int[] result = new int[num+1];
        for(int i=0; i <= num; i++){
            result[i] = countOne(i);
        }
        return result;
    }
    public int countOne(int n){
        int count = 0;
        while(n != 0){
            count+=(n & 1);
            n = n >> 1;
        }
        return count;
    }
}