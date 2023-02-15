package leetcode.hard;

import leetcode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
* 23. Merge k Sorted Lists
Hard
15.1K
571
Companies
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.



Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
*/
public class MergeKSortedLists {
    //simple problem not a hard at all
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists == null || lists.length==0){
                return null;
            }
            PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(Comparator.comparing(a -> a.val));

            ListNode dummy = new ListNode(0);
            ListNode result = dummy;
            for(int i=0;i<lists.length;i++){
                if(lists[i]!=null)
                    pq.add(lists[i]);
            }
            while(!pq.isEmpty()){
                ListNode tmp = pq.poll();
                if(tmp.next != null)
                    pq.add(tmp.next);
                result.next=tmp;
                result = result.next;

            }
            return dummy.next;
        }
}
