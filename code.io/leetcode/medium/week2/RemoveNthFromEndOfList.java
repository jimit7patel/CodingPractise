package leetcode.medium.week2;

import leetcode.ListNode;

/*
* 19. Remove Nth Node From End of List
Medium

13365

550

Add to List

Share
Given the head of a linked list, remove the nth node from the end of the list and return its head.



Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
*/
public class RemoveNthFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return head;
        }

        int size = size(head);
        int k = size - n;
        if(k < 0){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        int i =0;
        while(i!=k){
            cur = cur.next;
            prev = cur;
        }

        prev.next = cur.next;
        cur.next = null;

        return dummy.next;

    }

    public ListNode onePass(ListNode head, int n){
        ListNode first = head;
        int i =0;
        while(i++<n){
            first = first.next;
        }
        ListNode second = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while(first != null){
            prev = second;
            first = first.next;
            second = second.next;
        }

        prev.next = second.next;
        second.next = null;
        return dummy.next;


    }

    public int size(ListNode node){
        int size = 0;
        while(node != null){
            size++;
            node = node.next;
        }
        return size;
    }

}
