package leetcode.easy.week1;

import java.util.Deque;
import java.util.LinkedList;

/*
* Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.



Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
There is an iterative solution available but do not focus on that
*/
public class BackSpaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        Deque<Character> stack1 = new LinkedList<>();
        Deque<Character> stack2 = new LinkedList<>();
        for(char c: s.toCharArray()){
            if(c == '#'){
                stack1.pollFirst();
            }else{
                stack1.push(c);
            }
        }
        for(char c: t.toCharArray()){
            if(c == '#'){
                stack2.pollFirst();
            }else{
                stack2.push(c);
            }
        }
        if(stack1.size()!=stack2.size()){
            return false;
        }
        while(!stack1.isEmpty()){
            if(stack1.pop()!=stack2.pop()){
                return false;
            }
        }
        return true;


    }
}
