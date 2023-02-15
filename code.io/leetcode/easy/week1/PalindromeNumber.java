package leetcode.easy.week1;
/*
* Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward.

For example, 121 is a palindrome while 123 is not.


Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
*/
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        return optimized(x);
    }
    public boolean approach1(int x){
        if(x < 0){
            return false;
        }
        int revertedNumber = 0;
        int n = x;
        while(n > 0){
            revertedNumber = (revertedNumber*10) + n % 10;
            n = n/10;
        }
        return x == revertedNumber;
    }
    public boolean optimized(int x){
        if(x < 0 || (x%10 == 0 && x != 0)){
            return false;
        }
        int revertedNumber = 0;
        //only reverse half
        while(x > revertedNumber){
            revertedNumber = (revertedNumber*10) + x % 10;
            x = x/10;
        }
        return x == revertedNumber || x == revertedNumber/10;
    }

    public static void main(String[] args){
        System.out.println(new PalindromeNumber().isPalindrome(10));
    }
}
