package com.learn.thread.local;

/**
 * @Author: HuaChenG
 * @Description: 空指针ThreadLocal
 * @Date: Create in 2020/04/23
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }
    //如果没初始化longThreadLocal，小写long会造成空指针
    //因为ThreadLocal包装的是Long类型的，如果返回long类型，会做一个Long类型的装箱拆箱过程会导致空指针
    public Long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        }).start();
    }
}
