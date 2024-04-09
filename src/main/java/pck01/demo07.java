package pck01;

import java.util.concurrent.Semaphore;

/**
 * semaphore:用来控制线程的并发数量、限制对共享资源访问的线程数
 */
public class demo07 {
    static Semaphore semaphore = new Semaphore(2);
    public static void main(String[] args) {
        Thread[]threads=new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i]=new myThread();
            threads[i].start();
        }
    }
    static class myThread extends Thread {
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " 抢到了");//模拟共享资源
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
