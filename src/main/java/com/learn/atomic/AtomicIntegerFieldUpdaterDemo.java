package com.learn.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/13
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    static Candidate tom;
    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++;
            scoreUpdater.getAndIncrement(tom);
        }
    }


    public static class Candidate {
        volatile int score;
    }

    public static void main(String[] args) throws InterruptedException {
        tom = new Candidate();
        peter = new Candidate();
        AtomicIntegerFieldUpdaterDemo r = new AtomicIntegerFieldUpdaterDemo();

        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(peter.score);
        System.out.println(tom.score);

    }
}
