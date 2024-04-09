package pck01;

public class demo08 extends Thread {
    volatile boolean flag = true;
    @Override
    public void run() {
        while (flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("running");
        }
    }
    public void off(){
        flag = false;
    }

    public static void main(String[] args) {
        demo08 demo08 = new demo08();
        demo08.start();
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        demo08.off();
    }
}
