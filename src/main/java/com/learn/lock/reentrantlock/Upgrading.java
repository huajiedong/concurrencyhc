package com.learn.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/11
 */
public class Upgrading {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void readUpgrading() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "：得到了读锁，正在读取");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "：升级会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "：获取到写锁，升级成功");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "：释放了写锁");
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "：释放了读锁");

        }
    }

    private static void writeDownGrading() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "：得到了写锁，正在写入");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "：在不释放写锁的情况下，直接获取读锁，成功降级");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "：释放了读锁");
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "：释放了写锁");

        }
    }

    public static void main(String[] args) {
        System.out.println("支持锁的降级，不支持锁的升级，目的是避免死锁");
        new Thread(() ->writeDownGrading(), "Thread1").start();

//        new Thread(() ->readUpgrading(), "Thread2").start();

    }

}
