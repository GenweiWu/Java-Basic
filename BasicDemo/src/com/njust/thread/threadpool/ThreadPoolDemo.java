package com.njust.thread.threadpool;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;

/**
 * 解释下为什么线程池执行完代码后，不会自动退出程序
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
//        threadPoolDemo.test();
        threadPoolDemo.testAutoExit01();
        threadPoolDemo.testAutoExit02();
    }

    /**
     * 通过设置corePoolSize为0
     */
    private void testAutoExit01() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
            }
        });
    }

    /**
     * 通过allowCoreThreadTimeOut为true允许空闲的也释放
     */
    private void testAutoExit02() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
                1L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        executor.allowCoreThreadTimeOut(true);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
            }
        });
    }

    //不会自动退出程序
    private void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(this);
            }
        });
    }
}
