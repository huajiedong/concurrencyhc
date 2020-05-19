package com.learn.colletions.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: HuaChenG
 * @Description: 组合操作并不保证线程安全
 * @Date: Create in 2020/05/18
 */
public class OptionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
        OptionsNotSafe runnable = new OptionsNotSafe();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(scores.get("小明"));
    }

    @Override
    public void run() {
        //只能保证同时get或同时put的情况下是线程安全的，同时get又put是会出现线程不安全的情况
        for (int i = 0; i < 1000; i++) {
            /*synchronized (OptionsNotSafe.class) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                scores.put("小明", newScore);
            }*/
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                //replace方法是一个原子操作，
                boolean stat = scores.replace("小明", score, newScore);
                if (stat) {
                    break;
                }
            }
        }
    }
}
