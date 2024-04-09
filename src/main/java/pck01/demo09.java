package pck01;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock:读读不互斥，写写互斥，读写互斥
 */
public class demo09 {
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    public static void read(){
        readLock.lock();
        try {
            System.out.println("reading...");
        } finally {
            readLock.unlock();
        }
    }
    public static void write(){
        writeLock.lock();
        try {
            System.out.println("writing...");
        } finally {
            writeLock.unlock();
        }
    }
    public static void main(String[] args) {
        Runnable r1=()->{
            read();
        };
        Runnable r2=()->{
            read();
        };
        Runnable r3=()->{
            write();
        };
        Thread task1=new Thread(r1);
        Thread task2=new Thread(r2);
        Thread task3=new Thread(r3);
        task1.start();
        task2.start();
        task3.start();
    }
}
