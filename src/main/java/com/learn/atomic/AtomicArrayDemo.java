package com.learn.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/13
 */
public class AtomicArrayDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Incrementer decrementer = new Incrementer(atomicIntegerArray);
        Thread[] threadsIncrementer = new Thread[1000];
        Thread[] threadsDecrementer = new Thread[1000];
        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i] = new Thread(decrementer);
            threadsIncrementer[i] = new Thread(incrementer);
            threadsDecrementer[i].start();
            threadsIncrementer[i].start();
        }
        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i].join();
            threadsIncrementer[i].join();
        }
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现了非零的错误： 、" + i);
            }
        }
        System.out.println("运行结束");
    }

}
class Decrementer implements Runnable {

    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}
class Incrementer implements Runnable {

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}