package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/*
* 227. Basic Calculator II
Medium
5.2K
666
Companies
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5
*/
public class BasicCalculatorII {


    //the ideaIs tO keep pushing +And - numbersAnd keep pushing results of * and  / toStack
    public int calculate(String s){
        Deque<Integer> stack = new LinkedList<>();
        int currentNumber = 0;
        char op = '+';
        int result = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            if(Character.isDigit(c)){
                currentNumber = currentNumber * 10 + (c-'0');
            }
            if((!Character.isDigit(c) && !Character.isWhitespace(c))|| s.length()-1==i){
                if(op == '+'){
                    stack.addFirst(currentNumber);
                }
                if(op == '-'){
                    stack.addFirst(currentNumber*(-1));
                }
                if(op == '*'){
                    stack.addFirst(stack.removeFirst() * currentNumber);
                }
                if(op == '/'){
                    stack.addFirst(stack.removeFirst()/currentNumber);
                }
                currentNumber = 0;
                op = c;
            }
        }
        while(!stack.isEmpty()){
            result += stack.removeFirst();
        }
        return result;
    }

    //the idea is same but doNot use stack so, store the resultsOf +And -, and it isLooking behind
    public int calculateWithoutStack(String s){
        int currentNumber = 0;
        char op = '+';
        int result = 0;
        int prevOp = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                currentNumber = currentNumber * 10 + (c-'0');
            }
            if((!Character.isDigit(c) && !Character.isWhitespace(c))|| s.length()-1==i){
                if(op == '+'){
                    result += prevOp;
                    prevOp = currentNumber;
                }
                if(op == '-'){
                    result += prevOp;
                    prevOp -= currentNumber;
                }
                if(op == '*'){
                    prevOp = prevOp * currentNumber;
                }
                if(op == '/'){
                    prevOp = prevOp / currentNumber;
                }
                currentNumber = 0;
                op = c;
            }
        }
        return result + prevOp;
    }
    public static void main(String[] args){
        new BasicCalculatorII().calculate(" 3+5 / 2 ");
    }
}
