package leetcode.hard;

import leetcode.ListNode;

/*
* 25. Reverse Nodes in k-Group
Hard
10K
558
Companies
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.



Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:


Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
*/
public class ReverseNodesInKgroup {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

        public ListNode reverseKGroup(ListNode head, int k) {

            if(head == null || head.next == null){
                return head;
            }
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode cur = head;
            ListNode prev = dummy;
            int size=0,i=0;
            while(cur!=null){
                ++size;
                cur = cur.next;
            }
            while(i+k<=size){
                 prev = reverseBetween(prev,i+1,i+k);
                 i+=k;
            }
            return dummy.next;
        }
        public ListNode reverseBetween(ListNode prev, int m, int n) {
            ListNode con = prev;
            ListNode cur = prev.next;
            ListNode tail = cur;
            for(int i=0; i<=(n-m); i++){
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }
            con.next = prev;
            tail.next = cur;
            return tail;
        }

        public ListNode reverseKGroupRecursive(ListNode head, int k) {

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
        /*Not intuitive so avoid it
        * for(int i=0;i<(n-m);i++){
            cur.next = next.next;
            next.next = prev.next;
            prev.next = next;
            next = cur.next;
        }*/
}
