package com.njust.thread;


import java.util.UUID;

/**
 * 1、ThreadLocal的使用
 * 2、ThreadLocal--自定义初始值
 */
public class ThreadLocalDemo {
    private static ThreadLocal<String> HOLDER = ThreadLocal.withInitial(() -> "Up!");

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        threadLocalDemo.testSimple();
    }

    /**
     * testSimple:从下面的值可以看出初始化值为自定义内容+每个线程可以获取各自的线程的数据
     * <p>
     * init :Up!
     * init :Up!
     * init :Up!
     * getValue: b7abbd88-7697-4dcc-b907-e3a583748f26
     * getValue: 85258158-6828-415c-88b3-f3d4934da873
     * getValue: 60bc0772-aaf5-4016-b970-46b39d821c41
     */
    private void testSimple() {
        Task task = new Task();
        for (int i = 1; i <= 3; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    public class Task implements Runnable {
        @Override
        public void run() {
            test01();
            test02();
        }

        private void test01() {
            String detail = HOLDER.get();
            System.out.println("init :" + detail);

            HOLDER.set("" + UUID.randomUUID());
        }

        private void test02() {
            System.out.println("getValue: " + HOLDER.get());
        }
    }
}


