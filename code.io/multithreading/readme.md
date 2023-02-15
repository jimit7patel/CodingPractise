Volatile vs atomic integer

volatile is for visibility problems means, two threads are running while 
loop based on some flag, then flag should be declared as volatile otherwise each thread will have its local flag and its value updated by another thread will never be reflected.

atomic integer: this is for synchronization problem. if you have counter or i++ in multi thread, you need atomicInteger
it will only update new value B if the existing value matches to A(expected value), if expected value changed by another thread, it will not do anything.
more info. https://www.youtube.com/watch?v=WH5UvQJizH0&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6
https://www.baeldung.com/java-atomic-variables


https://www.youtube.com/watch?v=_RSAS-gIjGo&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=17
how to stop threads:
thread.interrupt(), future.cancel(), future.get(8,) -> timeout exception
use while thread is not interrupted, or use volatile boolean keepRunning

