package ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//this is using Lock
public class ProConWithWaitNotify<E> {

    private Object added = new Object();
    private Object removed = new Object();
    int count = 0;
    private Queue<E> queue;
    private int max = 16;
    public ProConWithWaitNotify(int size){
        queue = new LinkedList<>();
        this.max = size;
    }
    static final int MAX_COUNT = 100;
    public synchronized void produce() throws InterruptedException {
            while (count == MAX_COUNT) {
                removed.wait();
            }
            //addData();
            added.notify();

    }
    public synchronized void consume() throws InterruptedException {
            while (count == 0) {
                added.wait();
            }
            //getData();
            removed.notify();

    }
}

