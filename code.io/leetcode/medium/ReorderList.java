package leetcode.medium;

import leetcode.ListNode;

//simple but error prune to code
public class ReorderList {
    public void reorderList(ListNode head) {
        ListNode cur = head;
        ListNode end = findMidandThenReverse(head);
        while(cur != null && end!= null){
            ListNode t = cur.next;
            ListNode p = end.next;
            cur.next = end;
            end.next = t;
            cur = t;
            end = p;
        }
    }
    public ListNode findMidandThenReverse(ListNode head){
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode pre = null;
        ListNode t = slow.next;
        slow.next = null;
        slow = t;
        while(slow!= null){
            t = slow.next;
            slow.next = pre;
            pre = slow;
            slow = t;
        }
        return pre;
    }
}
