package com.learn.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/20
 */
public class OneShortLatch {

    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() {
        sync.acquireShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1:-1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShortLatch oneShortLatch = new OneShortLatch();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");
                    oneShortLatch.await();
                    System.out.println(Thread.currentThread().getName() + "获取成功，继续运行");
                }
            }).start();
        }
        Thread.sleep(5000);
        oneShortLatch.signal();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");
                oneShortLatch.await();
                System.out.println(Thread.currentThread().getName() + "获取成功，继续运行");
            }
        }).start();
    }
}
