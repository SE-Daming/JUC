package demos;
//如何保证线程1开始
public class 三线程交替打印ABC {
    volatile int currentThread=1;

    public static void main(String[] args) {
        三线程交替打印ABC t=new 三线程交替打印ABC();
        t.print();
    }

    public void print(){
        Object lock=new Object();
        Thread t1=new Thread(()->{
           while (true) {
               synchronized (lock){
                   if (currentThread==1) {
                       System.out.println(Thread.currentThread().getName()+" A");
                       currentThread=2;
                       lock.notifyAll();
                   }else {
                       try {
                           lock.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
        });

        Thread t2=new Thread(()->{
            while (true) {
                synchronized (lock){
                    if (currentThread==2) {
                        System.out.println(Thread.currentThread().getName()+" B");
                        currentThread=3;
                        lock.notifyAll();
                    }else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t3=new Thread(()->{
            while (true) {
                synchronized (lock){
                    if (currentThread==3) {
                        System.out.println(Thread.currentThread().getName()+" C");
                        currentThread=1;
                        lock.notifyAll();
                    }else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
