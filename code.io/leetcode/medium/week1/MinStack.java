package leetcode.medium.week1;
/*
* Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.
Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

*/
public class MinStack {
    class Node{
        int val, min;
        Node next;
        Node(int val){
            this.val = val;
        }
        Node(){}
    }
    private Node top=null;
    public MinStack() {
    }

    public void push(int val) {
        if(top == null){
            top = new Node(val);
            top.min = val;
        }else{
            Node t = new Node(val);
            t.next = top;
            t.min = Math.min(top.min, val);
            top = t;
        }
    }

    public void pop() {
       top = top.next;
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return top.min;
    }
}
/* this using stack
 Deque<Pair<Integer,Integer>> q = new LinkedList<>();
    public MinStack() {

    }

    public void push(int x) {
        if(q.isEmpty()){
            q.addFirst(new Pair(x,x));
        }else{
            q.addFirst(new Pair(x,Math.min(x,q.getFirst().getValue())));
        }
    }

    public void pop() {
        //if(!q.isEmpty()){
        q.pollFirst();
        //}
    }

    public int top() {
        return q.peekFirst().getKey();
    }

    public int getMin() {
        return q.peekFirst().getValue();
    }
    */