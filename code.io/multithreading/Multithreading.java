package multithreading;
//advantage of runnable is you can have multi level extension
public class Multithreading extends Thread{

    private int threadNumber;
    public Multithreading (int threadNumber){
        this.threadNumber = threadNumber;
    }
    @Override
    public void run(){
        for(int i=0; i<5; i++){
            System.out.println(i + "from thread " + threadNumber);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<5; i++) {
            Multithreading my = new Multithreading(i);
            my.start();
            /*Multithreading my2 = new Multithreading();

            //my.run(); wont be multi-threading
            my.start(); // this is creating single thread
            my2.start();
            */
            my.join(); // this waits for thread to die, in this case, it will wait for thread 1 to terminate
            // before creating new one. so it defeats parallel programming

        }

    }
}
class MultithreadUsingRun implements Runnable{

    private int threadNumber;
    public MultithreadUsingRun (int threadNumber){
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for(int i=0; i<5; i++){
            System.out.println(i + "from thread " + threadNumber);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args){
        for(int i=0; i<5; i++) {
            Multithreading my = new Multithreading(i);
            Thread t = new Thread(my);
            t.start();
            /*Multithreading my2 = new Multithreading();

            //my.run(); wont be multi-threading
            my.start(); // this is creating single thread
            my2.start();
            */

        }
        //using lambda expression
        Thread t = new Thread(() -> {
           //run method
        });

    }
}
