package com.learn.thread.local;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/04/23
 */
public class ThreadLocalNormalUsage00 {

    public String data(int seconds) {
        //参数单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().data(10);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().data(104707);
                System.out.println(date);
            }
        }).start();
    }
}
