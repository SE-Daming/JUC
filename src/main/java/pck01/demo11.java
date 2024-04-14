package pck01;

import java.util.concurrent.*;

public class demo11 {
    /**
     Executors工具类创建线程池
     */
    ExecutorService executor=Executors.newFixedThreadPool(3);
    ExecutorService executor2=Executors.newCachedThreadPool();
    ExecutorService executor3=Executors.newSingleThreadExecutor();
    ExecutorService executor4=Executors.newScheduledThreadPool(3);

    /**
        ThreadPoolExecutor 类来创建自定义的线程池
     *  参数(核心线程数、最大线程数、线程空闲时间、时间单位、任务队列)
     */
    ExecutorService executor5=new ThreadPoolExecutor(3,5,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));

    /**
     * 提交任务的方式
     * submit(Runnable/Callable)
     * execute(Runnable)
     *
     */
    void submitTask(Runnable runnableTask,Callable callableTask){
        executor.submit(runnableTask);
        executor.submit(callableTask);
        executor.execute(runnableTask);
    }
}
