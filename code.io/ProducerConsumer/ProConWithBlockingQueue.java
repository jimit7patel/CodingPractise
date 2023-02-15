package ProducerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProConWithBlockingQueue {
    public static void main(String[] args){
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        final Runnable producer = () ->{
            while(true){
                try {
                    queue.put(new Random().nextInt());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(producer).start();
        new Thread(producer).start();

        final Runnable consumer = () ->{
            while(true){
                try {
                    int i =  queue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        new Thread(consumer).start();
        new Thread(consumer).start();

    }
}
