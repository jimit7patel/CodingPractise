package leetcode.easy.week1;

import java.util.Deque;
import java.util.LinkedList;

/*
* Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
* */
public class ValidParentheses {
    public boolean isValid(String s) {

        Deque<Character> stack = new LinkedList<>();
        int i=0;
        while(i != s.length()){
            char c = s.charAt(i);
            if(IsOpeningBracket(c)){
                stack.addFirst(c);
            }
            else{
                if(!stack.isEmpty()) {
                    char openingBracket = stack.removeFirst();
                    if (!IsMatchingBracket(openingBracket, c)) {
                        return false;
                    }
                }else{
                    return false;
                }
            }
            i++;
        }
        return stack.isEmpty();

    }

    private boolean IsOpeningBracket(char c){
        if(c == '(' || c == '{' || c=='['){
            return true;
        }
        return false;
    }

    private boolean IsMatchingBracket(char o, char c){
        if((c == ')' && o == '(') || (c == '}' && o == '{') || (c==']' && o == '[')){
           return true;
        }
        return false;
    }
}
