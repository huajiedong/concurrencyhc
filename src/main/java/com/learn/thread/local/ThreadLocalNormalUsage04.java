package com.learn.thread.local;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/04/23
 */
public class ThreadLocalNormalUsage04 {
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public String data(int seconds) {
        //参数单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        String s;
        synchronized (ThreadLocalNormalUsage04.class) {
            s = simpleDateFormat.format(date);
        }
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage04().data(finalI);
                    System.out.println(date);
                }
            });
        }
        executorService.shutdown();
    }
}
