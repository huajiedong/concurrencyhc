package com.learn.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/13
 */
public class AtomicIntegerDemo1 implements Runnable {

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic() {
        atomicInteger.getAndIncrement();
//        atomicInteger.getAndDecrement();
//        atomicInteger.getAndAdd(10);
    }

    private static volatile int basicCount = 0;

    public void incrementBasic() {
        basicCount++;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 demo1 = new AtomicIntegerDemo1();
        Thread thread1 = new Thread(demo1);
        Thread thread2 = new Thread(demo1);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("atomicInteger :" + atomicInteger.get());
        System.out.println("basicCount :" + basicCount);

    }
}
