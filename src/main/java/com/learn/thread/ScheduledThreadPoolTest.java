package com.learn.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/04/22
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        scheduledExecutorService.schedule(new Tast(), 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Tast(), 1, 3, TimeUnit.SECONDS);


    }
}
