package com.learn.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/11
 */
public class PessimismOptimismLock {
    int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
    public synchronized void testMethod() {
        a ++;
    }
}
