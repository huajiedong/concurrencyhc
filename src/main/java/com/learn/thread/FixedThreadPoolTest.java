package com.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/04/22
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Tast());
        }
    }

}
class Tast implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
