package com.learn.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/13
 */
public class AtomicLongDemo {
    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService service = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();
        while (!service.isTerminated()) {

        }
        long end = System.currentTimeMillis();
        System.out.println("AtomicLong耗时: " + (end - start));
        System.out.println(counter.get());
    }
    private static class Task implements Runnable {
        private AtomicLong counter;
        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.getAndIncrement();
            }
        }
    }
}
