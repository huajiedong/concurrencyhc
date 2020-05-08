package com.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/04/22
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Tast());
        }
    }
}
