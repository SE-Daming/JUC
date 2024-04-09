package pck01;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo05 extends Thread{
    Counter counter;
    public demo05(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int size=1000;
        Counter counter = new Counter();
        demo05[]threads = new demo05[size];
        for(int i=0;i<size;i++){
            threads[i]=new demo05(counter);
            threads[i].start();
        }
        for(int i=0;i<size;i++){
            threads[i].join();
        }
        System.out.println(counter.get());
    }
    static class Counter {
        private int count = 0;
        final Lock lock=new ReentrantLock();
        public  void add() {
            lock.lock();
            try {
                count++;
            }finally {
                lock.unlock();
            }
        }
        public int get() {
            return count;
        }
    }
}
