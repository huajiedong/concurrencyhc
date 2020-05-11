package com.learn.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/07
 */
public class MustUnLock {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            //获取锁保护的资源
            System.out.println(Thread.currentThread().getName() + ": 开始执行任务");
        } finally {
            lock.unlock();
        }
    }
}
