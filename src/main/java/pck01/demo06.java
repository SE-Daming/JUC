package pck01;

import java.util.concurrent.CountDownLatch;

public class demo06 {
    static CountDownLatch latch=new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new myThread().start();
        }
        latch.await();
        System.out.println("main线程执行完毕");
    }
    static class myThread extends Thread{
        @Override
        public void run() {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"开始执行");
            latch.countDown();
        }
    }
}
