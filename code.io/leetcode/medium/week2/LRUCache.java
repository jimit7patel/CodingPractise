package leetcode.medium.week2;

import java.util.HashMap;

/*
* 146. LRU Cache
Medium

14936

604

Add to List

Share
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
*/
public class LRUCache {
    private class Node{
        int key,value;
        Node prev, next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    private int MAX_CAPACITY ;
    private HashMap<Integer, Node> map;
    public LRUCache(int capacity){
        MAX_CAPACITY = capacity;
        map = new HashMap<>();
    }
    Node head, tail;
    public int get(int key){
        if(map.containsKey(key)){
            Node v = map.get(key);
            if(v == head){
                return v.value;
            }
            remove(v);
            addFirst(v);
            return v.value;
        }
        return -1;
    }

    public void put(int key, int value){
        Node n;
        if(map.containsKey(key)){
            n = map.get(key);
            remove(n);
        }else{
            if(map.size() == MAX_CAPACITY){
                map.remove(tail.key);
                remove(tail);
            }
            n = new Node(key, value);
            map.put(key, n);
        }
        n.value = value;
        addFirst(n);
    }
    public void remove(Node v){
        Node p = v.prev;
        Node n = v.next;
        if(head == tail){
            head = tail = null;
            return;
        }
        if(v == tail){
            tail.next = null;
            tail = n;
            return;
        }
        if(v == head){
            head.prev = null;
            head = p;
            return;
        }
        p.next=n;
        n.prev = p;
    }

    public void addFirst(Node v){
        if(head == null){
            head = tail = v;
            return;
        }
        head.next = v;
        v.prev = head;
        head = v;
    }

    public static void main(String[] args){
        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        cache.put(2,2);
        cache.get(2);
        cache.put(1,1);
        cache.put(4,1);
        cache.get(2);
    }

}
