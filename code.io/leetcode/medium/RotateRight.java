package leetcode.medium;

import leetcode.ListNode;

/*
* 61. Rotate List
Medium
7K
1.3K
Companies
Given the head of a linked list, rotate the list to the right by k places.



Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:


Input: head = [0,1,2], k = 4
Output: [2,0,1]


Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
*/
public class RotateRight {
    //easy to figure out but messy to code. look at intuitive version
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null){
            return head;
        }

        ListNode p1 = new ListNode();
        ListNode con1 = p1;
        ListNode p2 = new ListNode();
        ListNode con2 = p2;
        int size=0;
        ListNode s = head;
        while(s!=null){
            s=s.next;
            size++;
        }
        int rotate = size - k%size;

        while(rotate-->0){
            p2.next=head;
            p2=p2.next;
            head=head.next;
        }
        while(head!=null){
            p1.next=head;
            p1=p1.next;
            head=head.next;
        }

        p1.next=con2.next;
        p2.next=null;
        return con1.next;
    }
    public ListNode rotateRightIntuitive(ListNode head, int k) {
        if(head==null || head.next==null){
            return head;
        }

        ListNode dummy1 = new ListNode();
        ListNode dummy2 = new ListNode();
        dummy1.next = head;
        dummy2.next = head;
        ListNode p1 = new ListNode();
        ListNode p2 = new ListNode();
        int size=0;
        ListNode s = head;
        while(s!=null){
            s=s.next;
            size++;
        }
        int rotate = size - k%size;

        while(rotate-->0){
            p2 = head;
            head = head.next;
        }
        if(head != null){
            dummy2.next = head;
        }
        while(head!=null){
            p1 = head;
            head = head.next;
        }
        p2.next = null;
        p1.next = dummy1.next;
        return dummy2.next;
    }
}
