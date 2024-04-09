package pck01;

/**
 * isInterrupted()：判断当前线程是否中断，如果中断，则返回true，否则返回false。
 * interrupted()：判断当前线程是否中断，如果中断，则返回true，否则返回false，并且清除中断状态。
 */
public class demo01 extends Thread{
    @Override
    public void run() {
        while (true){
            if(isInterrupted()){

                System.out.println(isInterrupted());//true
                System.out.println(isInterrupted());//true

                System.out.println(interrupted());//true
                System.out.println(interrupted());//false
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        demo01 t1 = new demo01();
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
