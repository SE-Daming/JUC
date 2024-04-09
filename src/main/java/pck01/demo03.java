package pck01;

import java.util.ArrayList;
import java.util.List;

public class demo03 extends Thread{
    static List<String>task=new ArrayList<>();
    static Object lock=new Object();
    public static void main(String[] args) {
        producer p=new producer();
        consumer c=new consumer();
        Thread t1=new Thread(p);
        Thread t2=new Thread(c);
        t1.start();
        t2.start();
    }
    static class producer implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (lock){
                    while (task.size()==10){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("producer: produced task");
                    task.add("task");
                    lock.notifyAll();
                }
            }
        }
    }
    static class consumer implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (lock){
                    while (task.size()==0){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("consumer: consumed task");
                    task.remove(0);
                    lock.notifyAll();
                }
            }
        }
    }
}
