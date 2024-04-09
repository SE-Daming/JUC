package pck01;

/**
 * 在线程阻塞时调用interrupt()方法，会清除interrupt（变成false）
 */
public class demo02 extends Thread{
    public void run(){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    public static void main(String[] args) throws InterruptedException {
        demo02 d=new demo02();
        d.start();
        Thread.sleep(10);
        d.interrupt();
        d.join();
        System.out.println("main:"+d.isInterrupted());//false
    }
}
