package pck01;



public class demo04 extends Thread{
     Counter counter;
    public demo04(Counter counter) {
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
        demo04[]threads = new demo04[size];
        for(int i=0;i<size;i++){
            threads[i]=new demo04(counter);
            threads[i].start();
        }
        for(int i=0;i<size;i++){
            threads[i].join();
        }
        System.out.println(counter.get());
    }
    static class Counter {
        private int count = 0;
        public synchronized void add() {
            count++;
        }
        public int get() {
            return count;
        }
    }
}
