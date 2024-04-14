package pck01;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class demo10 {
    public static void main(String[] args) {
        int size=2000;
        long[]arr=new long[size];
        long expected=0;
        for (int i = 0; i < size; i++) {
            arr[i]= new Random().nextLong(100);
            expected+=arr[i];
        }
        task t=new task(arr,0,arr.length);
        Long res = t.compute();
        System.out.println("expect:"+expected);
        System.out.println("res:"+res);
    }
}
class task extends RecursiveTask<Long>{
    static final int THRESHOLD=100;
    private long[]arr;
    private int start;
    private int end;
    public task(long[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        if(end-start<=THRESHOLD){
            long sum=0;
            for (int i = start; i < end; i++) {
                sum+=arr[i];
            }
            return sum;
        }
        int mid=(start+end)/2;
        task left=new task(arr,start,mid);
        task right=new task(arr,mid,end);
        invokeAll(left,right);
        return left.join()+right.join();
    }
}
