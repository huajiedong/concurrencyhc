package com.learn.cas;

/**
 * @Author: HuaChenG
 * @Description: 模拟CAS操作，等价代码
 * @Date: Create in 2020/05/13
 */
public class TwoThreadsCompetitiom implements Runnable {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetitiom r = new TwoThreadsCompetitiom();
        r.value = 0;
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);

    }
}
