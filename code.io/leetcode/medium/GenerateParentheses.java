package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/*
* 22. Generate Parentheses
Medium
16.3K
634
Companies
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
*/
public class GenerateParentheses {
    private List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        helper("",0,0,n);
        return result;
    }
    public void helper(String s, int l, int r, int n){
        if(l==r && l == n){
            result.add(s);
            return;
        }
        if(l < n){
            helper(s + "(", l+1, r, n);
        }
        if(r < l){
            helper(s + ")", l, r+1, n);
        }
    }

    public void helper2(String s, int l, int r){
        if(l==r && l == 0){
            result.add(s);
            return;
        }
        if(l > 0){
            helper2(s + "(", l-1, r);
        }
        if(l < r && r > 0){
            helper2(s + ")", l, r-1);
        }
    }

}
