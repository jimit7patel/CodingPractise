package leetcode.easy.week1;

/*
* Given the head of a singly linked list, reverse the list, and return the reversed list.



Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

*/
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode c = head;
        ListNode prev = null;

        while(c!=null){
            ListNode tmp = c.next;
            c.next = prev;
            prev = c;
            c = tmp;
        }
        return prev;
    }
    //using recursion
    public ListNode helper(ListNode head, ListNode pre){
        if(head == null){
            return pre;
        }
        ListNode tmp = head.next;
        head.next = pre;
        return helper(tmp,head);
    }
}
