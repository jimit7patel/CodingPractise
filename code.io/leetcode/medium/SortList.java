package leetcode.medium;

import leetcode.ListNode;

/*
* 148. Sort List
Medium
8.9K
271
Companies
Given the head of a linked list, return the list after sorting it in ascending order.



Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]*/
public class SortList {

    public ListNode sortList(ListNode head) {
        if(head ==  null ||head.next == null){
            return head;
        }
        ListNode mid = mid(head);
        ListNode next = mid.next;
        mid.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(next);
        return merge(left,right);
    }
    public ListNode merge(ListNode node1, ListNode node2){
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(node1!=null && node2 != null){
            if(node1.val < node2.val){
                cur.next = node1;
                node1 = node1.next;
            }else{
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        if(node1!=null){
            cur.next = node1;
        }else{
            cur.next = node2;
        }
        return dummy.next;
    }
    public ListNode mid(ListNode node){
        ListNode slow = node;
        ListNode fast = node.next;
        while(fast!=null && fast.next!= null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
