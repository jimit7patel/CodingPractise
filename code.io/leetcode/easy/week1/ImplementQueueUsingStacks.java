package leetcode.easy.week1;

import java.util.Deque;
import java.util.LinkedList;

public class ImplementQueueUsingStacks {
    Deque<Integer> stack1 = new LinkedList<>();
    Deque<Integer> stack2 = new LinkedList<>();

    public ImplementQueueUsingStacks() {

    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if(!stack2.isEmpty()){
            return stack2.removeFirst();//pop
        }
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public int peek() {
        if(!stack2.isEmpty()){
            return stack2.peek();
        }
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
/*
* alternate approach no need
* Implement queue using one stack- use implicit stack recursion



Deque<Integer> e = new LinkedList<>();

    public MyQueue() {

    }
    public void push(int x) {
        e.addFirst(x);

    }
    public int pop() {

        if(e.size()==1){

            return e.removeFirst();

        }

        int x = e.removeFirst();

        int p = pop();

        e.addFirst(x);

        return p;

    }
    public int peek() {

        if(e.size()==1){

            return e.getFirst();

        }

        int x = e.removeFirst();

        int p = peek();

        e.addFirst(x);

        return p;

    }
    public boolean empty() {

        return  e.isEmpty();

    }
    */
