package leetcode.medium.week1;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.BiFunction;

/*
* 150. Evaluate Reverse Polish Notation
Medium

3690

675

Add to List

Share
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

*/
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for(String token: tokens){
            if(ifOperator(token)){
                int t = eval(stack.pop(), stack.pop(), token);
                stack.push(t);
            }else{
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
    public int eval(int s1, int s2, String token){
        if(token.equals("/")){
            return s2/s1;
        }else if(token.equals("+")){
            return s1 + s2;
        }else if (token.equals("-")){
            return s2 - s1;
        }else {
            return s1 * s2;
        }
    }
    public boolean ifOperator(String s){
        if(s.equals("/") || s.equals("*")  || s.equals("+") || s.equals("-")){
            return true;
        }
        return false;
    }

    public int moreConciseWayEvalRPN(String[] tokens) {
            Stack<Integer> stack = new Stack<>();

            for (String token : tokens) {
                switch (token) {
                    case "+" -> stack.push(stack.pop() + stack.pop());
                    case "-" -> stack.push(-(stack.pop() - stack.pop()));
                    case "*" -> stack.push(stack.pop() * stack.pop());
                    case "/" -> stack.push((int) ((1.00 / stack.pop()) * stack.pop()));
                    default ->  stack.push(Integer.parseInt(token));
                }
            }
            return stack.pop();
    }
    //below is extensible solution
    interface Operator{
        int eval(int x, int y);
    }

    private static final HashMap<String, Operator> OPERATORS = new HashMap<>(){{
        put("+", (x,y) -> x+y);
        put("-", (x,y) -> x-y);
        put("*", new Operator(){
            @Override
            public int eval(int x, int y){ return x*y; }
        });
        put("/", new Operator(){
            @Override
            public int eval(int x, int y){ return x/y; }
        });
    }};

    public int extensibleEval(String[] tokens) {
        Stack<Integer> st = new Stack<>();

        for(String token: tokens){
            if(OPERATORS.containsKey(token)){
                int y = st.pop();
                int x = st.pop();
                st.push(OPERATORS.get(token).eval(x,y));
            }
            else{
                st.push(Integer.parseInt(token));
            }
        }

        return st.pop();
    }

    //below is using java lambda
    public int lambdaEvalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (String t : tokens) {
            switch (t) {
                case "+" -> op(st, (a,b) -> b+a);
                case "-" -> op(st, (a,b) -> b-a);
                case "*" -> op(st, (a,b) -> b*a);
                case "/" -> op(st, (a,b) -> b/a);
                default -> st.push(Integer.valueOf(t));
            }
        }
        return st.pop();
    }

    private static void op(Stack<Integer> st, BiFunction<Integer, Integer, Integer> f) {
        st.push(f.apply(st.pop(), st.pop()));
    }

}
