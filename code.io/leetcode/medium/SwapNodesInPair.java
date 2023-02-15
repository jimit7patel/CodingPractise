package leetcode.medium;

import leetcode.ListNode;

/*
*
*
* https://leetcode.com/problems/swap-nodes-in-pairs/description/
* 24. Swap Nodes in Pairs
Medium
8.7K
342
Companies
Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)



Example 1:


Input: head = [1,2,3,4]
Output: [2,1,4,3]
Example 2:

Input: head = []
Output: []
Example 3:

Input: head = [1]
Output: [1]
*/
public class SwapNodesInPair {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        return iterative(head);
        //return recursive(head);
        //recurse(head,dummy);
        //return dummy.next;
        //return reverseKGroup(head,2);
    }


    public ListNode recursive(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode tmp = head.next;
        head.next = recursive(head.next.next);
        tmp.next = head;
        return tmp;
    }
    public void recurse(ListNode head, ListNode prev){
        if(head == null || head.next==null){
            return;
        }
        ListNode tmp = head.next.next;
        head.next.next=head;
        prev.next = head.next;
        head.next = tmp;
        prev = head;
        recurse(head.next,head);

    }
    public ListNode iterative(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        while(cur!=null && cur.next!=null){
            ListNode next = cur.next.next;
            cur.next.next=cur;
            prev.next = cur.next;
            cur.next = next;
            prev = cur;
            cur = next;
        }


        return dummy.next;
    }

    /*
    public ListNode reverseKGroup(ListNode head, int k) {

        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        int size=0;
        while(cur!=null){
            ++size;
            cur = cur.next;
        }
        reverseBetween(prev,k,0,size);
        return dummy.next;
    }

    public void reverseBetween(ListNode prev, int k, int i, int l) {
        if(l-i<k){
            return;
        }
        ListNode con = prev;
        ListNode cur = prev.next;
        ListNode tail = cur;
        int j=k;
        while(j-->0){
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        con.next = prev;
        tail.next = cur;
        reverseBetween(tail,k,i+k,l);
    }
    */
}

