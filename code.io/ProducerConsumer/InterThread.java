package ProducerConsumer;

class Q {
    int num;
    boolean valueSet = false;
    public synchronized void put (int num){
        while(valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Put: " + num);
        this.num = num;
        valueSet = true;
        notify();
    }
    public synchronized void get(){
        while(!valueSet){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Get: " + num);
        valueSet = false;
        notify();
    }
}

class Producer {
    Q q;
    public Producer(Q q) {
        this.q = q;
        Thread t = new Thread(() -> {
            int i = 0;
            while (true) {
                q.put(i++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }, "producer");
        t.start();
    }
}
class Consumer {
    Q q;
    public Consumer(Q q) {
        this.q = q;
        Thread t = new Thread( () -> {
            while (true) {
                q.get();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }, "consumer");
        t.start();
    }
}
public class InterThread {

    public static void main(String[] args){
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}
