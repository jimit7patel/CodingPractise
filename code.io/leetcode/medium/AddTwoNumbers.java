package leetcode.medium;

import leetcode.ListNode;

/*
* 2. Add Two Numbers
Medium
23.4K
4.5K
Companies
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.



Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
*/
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummy.next;
    }
    /*
     * Asymptotic complexity in terms of lengths of the first and second number, `lenA` and `lenB`:
     * Time: O(max(lenA, lenB)).
     * Auxiliary space: O(1).
     * Total space: O(lenA + lenB).
     */
    static ListNode add_two_numbers(ListNode l1, ListNode l2) {

        ListNode result = l2;
        // We are storing resultant sum in l2.
        int carryForward = 0, sum = 0;
        // To store carry and current sum.
        // We are iterating till we reach at end of one of linkedlist.
        // and update l2 with resultant sum.
        while(true){
            sum = l1.val + l2.val + carryForward;
            l2.val = sum % 10;
            carryForward = sum / 10;
            if(l1.next == null || l2.next == null){
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        // If we reached the end of l2 but not of l1, we can utilize the already created nodes
        // of l1 by appending them to l2.
        if(l1.next != null && l2.next == null){
            l2.next = l1.next;
        }
        // We iterate through remaining nodes of l2 and update it with sum of node and carry.
        while(carryForward > 0 && l2.next != null){
            l2 = l2.next;
            sum = carryForward + l2.val;
            l2.val = sum % 10;
            carryForward = sum / 10;
        }
        // If still carry is remaining then we add extra node at tail of linkedlist l2.
        if(carryForward > 0){
            ListNode new_node = new ListNode(carryForward);
            l2.next = new_node;
        }
        // Result will be head node of l2 (which is storing our resultant sum)
        return result;
    }
}
