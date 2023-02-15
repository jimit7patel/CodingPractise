package leetcode.medium.week1;

import java.util.Arrays;

/*
* You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
*/
public class CoinChange {
    int[][] dp ;
    public int coinChange(int[] coins, int amount) {
        dp = new int[amount+1][coins.length];
        Arrays.stream(dp)
                .forEach(i -> Arrays.fill(i,-1));
        int ans = memo(coins, amount, 0);
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    public int dp(int[] coins, int amount){
        dp = new int[amount+1][coins.length+1];
        Arrays.stream(dp)
                .forEach(i -> Arrays.fill(i, Integer.MAX_VALUE));
        Arrays.fill(dp[0], 0);
        for(int a=1; a <= amount; a++){
            for(int i=coins.length-1; i>=0; i--){
                int x = dp[a][i+1];
                if(a-coins[i] >=0 ){

                    x = Math.min(x, dp[a-coins[i]][i]==Integer.MAX_VALUE?Integer.MAX_VALUE:dp[a-coins[i]][i] + 1);
                }
                dp[a][i] = x;
            }
        }
        return dp[amount][0];
    }
    public int dpOptimized(int[] coins, int amount){
        int dpp[] = new int[amount+1];
        Arrays.fill(dpp, Integer.MAX_VALUE);
        dpp[0] = 0;
        for(int i =1; i<= amount; i++){
            for(int c: coins){
                if(i>=c) {
                    dpp[i] = Math.min(dpp[i], dpp[i - c] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dpp[i - c] + 1);
                }
            }
        }
        return dpp[amount]==Integer.MAX_VALUE?-1:dpp[amount];
    }

    public int memo(int[] coins, int amount, int i){
        if(amount < 0){
            return Integer.MAX_VALUE;
        }
        if(amount == 0){
            return 0;
        }
        if(dp[amount][i] != -1){
            return dp[amount][i];
        }
        int a = memo(coins, amount-coins[i], i);
        a = a==Integer.MAX_VALUE?a:a+1;
        if(i<coins.length-1){
           a = Math.min(a,memo(coins, amount, i+1));
        }
        dp[amount][i] = a;
        return a;
    }

    public static void main(String[] args){
        System.out.println(new CoinChange().dp(new int[]{1,2,5},11));
    }
}
