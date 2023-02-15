package doordash;

import java.util.ArrayList;
import java.util.List;

/*
* You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.

Implement the BrowserHistory class:

BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
void visit(string url) Visits url from the current page. It clears up all the forward history.
string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.


Example:

Input:
["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
Output:
[null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]

Explanation:
BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
*/
public class BrowserHistory {
    //this was a simple list approach.
    /*
    List<String> list;
    int pos ;

    public BrowserHistory(String homepage) {
        list = new ArrayList<>();
        list.add(homepage);
        pos = 0;
    }

    public void visit(String url) {
        list.subList(pos+1,list.size()).clear();
        list.add(url);
        pos=list.size()-1;
    }

    public String back(int steps) {
        pos = steps > pos?0:pos-steps;
        return list.get(pos);
    }

    public String forward(int steps) {
        pos = pos + steps >= list.size()? list.size()-1:pos + steps;
        return list.get(pos);
    }
     */
//below is doubly connected linkedlist approach

    Node cur;
    Node dummyHead = new Node("dummy");
    Node dummyTail = new Node("dummy");
    public BrowserHistory(String homepage) {
        Node n = new Node(homepage);
        dummyHead.prev = n;
        n.next = dummyHead;
        n.prev = dummyTail;
        dummyTail.next = n;
        cur = n;
    }

    public void visit(String url) {
        Node n = new Node(url);
        n.next = dummyHead;
        n.prev = cur;
        cur.next = n;
        dummyHead.prev=n;
        cur = n;
    }

    public String back(int steps) {
        while(steps-- > 0 && cur != dummyTail){
            cur = cur.prev;
        }
        cur = cur == dummyTail?cur.next:cur;
        return cur.val;
    }

    public String forward(int steps) {
        while(steps-- > 0 && cur != dummyHead){
            cur = cur.next;
        }
        cur = cur==dummyHead?cur.prev:cur;
        return cur.val;
    }
    class Node {
        Node prev, next;
        String val;
        Node (String val ){
            this.next=null;
            this.prev=null;
            this.val = val;
        }
    }

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
    public static void main (String[] args){
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
        browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
        browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
        browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
        browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
    }
}
