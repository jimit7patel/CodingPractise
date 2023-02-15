package leetcode.medium;
/*
* 50. Pow(x, n)
Medium
6.3K
6.7K
Companies
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).



Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-231 <= n <= 231-1
n is an integer.
-104 <= xn <= 104
*/
public class Powxy {

    public double myPow(double x, int n) {
        if(x==1)
            return x;
        double xx = iterative(x,n);
        //double xx = recursive(x,n);
        return n<0?1/xx:xx;
    }

    public double recursive(double x, int n){
        if(n == 0){
            return 1;
        }
        if(n%2 != 0){
            return x * recursive(x*x, n/2);
        }
        return recursive(x*x, n/2);
    }

    public double iterative(double x, int n){
        double ans = 1;
        if((n&1)==1){
            ans = x;
        }
        while(n > 1){
            x *= x;
            n >>= 1;
        }
        return ans * x;
    }
    public static void main(String[] args){
        System.out.println(new Powxy().myPow(2,5));
    }
}
