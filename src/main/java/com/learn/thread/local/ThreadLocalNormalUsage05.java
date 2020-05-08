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
public class ThreadLocalNormalUsage05 {
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public String data(int seconds) {
        //参数单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal2.get();
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage05().data(finalI);
                    System.out.println(date);
                }
            });
        }
        executorService.shutdown();
    }
}
class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

}