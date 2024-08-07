package demos;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class 双线程交替打印奇偶 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger n=new AtomicInteger(0);
        Object lock=new Object();
        Thread t1=new Thread(()->{
            while(true&&n.get()<100){
                synchronized (lock){
                    if(n.get()%2==1){
                        System.out.println(Thread.currentThread().getName()+" :"+n.getAndIncrement());
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread t2=new Thread(()->{
            while(true&&n.get()<=100){
                synchronized (lock){
                    if(n.get()%2==0){
                        System.out.println(Thread.currentThread().getName()+" :"+n.getAndIncrement());
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
        sleep(1000);
    }
}
