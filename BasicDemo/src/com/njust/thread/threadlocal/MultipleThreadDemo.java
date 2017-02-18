package com.njust.thread.threadlocal;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ThreadLocal--多线程问题
 */
public class MultipleThreadDemo {
    private static ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static void main(String[] args) {
        MultipleThreadDemo multipleThreadDemo = new MultipleThreadDemo();
//        multipleThreadDemo.testWithThread();
        multipleThreadDemo.testWithThreadPool();
    }

    /**
     * 1.2 验证线程池下可能有问题(如果不是每个线程，每次都设置下值的话)
     * 注意线程池下的线程出现重用时，值重复出现了！！！
     * Thread[pool-1-thread-1,5,main]-->a7becfb6-81ca-4cec-8a8f-e8a59b1c5bb3
     * Thread[pool-1-thread-2,5,main]-->4176f47f-09f3-461d-a2d9-c9b8e7acbe0c
     * Thread[pool-1-thread-2,5,main]-->4176f47f-09f3-461d-a2d9-c9b8e7acbe0c
     */
    private void testWithThreadPool() {
        Task task = new Task();

        Executor executor = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 3; i++) {
            executor.execute(task);
        }
    }

    /**
     * 1.1 验证多线程下使用正常
     * <p>
     * Thread[Thread-0,5,main]-->b664514f-3261-4096-a4ac-44449ae3d681
     * Thread[Thread-1,5,main]-->f15ea2e2-1778-41d3-b6d1-92c44304e9dd
     * Thread[Thread-2,5,main]-->ba6edb1c-e186-4af2-b462-08ca606b6684
     */
    private void testWithThread() {
        Task task = new Task();
        for (int i = 1; i <= 3; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    public class Task implements Runnable {
        @Override
        public void run() {
            String detail = HOLDER.get();
            if (detail == null) {
                detail = "" + UUID.randomUUID();
                HOLDER.set(detail);
            }
            System.out.println(Thread.currentThread() + "-->" + detail);
        }
    }
}
