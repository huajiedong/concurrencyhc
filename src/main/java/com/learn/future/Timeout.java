package com.learn.future;

import java.util.concurrent.*;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author: HuaChenG
 * @Description: 演示get超时方法，需要注意超时后处理，
 * 调用future.cancel(),演示cancel传入true和false的区别
 * 代表是否中断正在执行的任务
 * @Date: Create in 2020/05/20
 */
public class Timeout {

    private double x, y;final StampedLock sl = new StampedLock();
    // 存在问题的方法
     void moveIfAtOrigin(double newX, double newY){
         long stamp = sl.readLock();
         try { while(x == 0.0 && y == 0.0){
             long ws = sl.tryConvertToWriteLock(stamp);
             if (ws != 0L) {
                 x = newX; y = newY;
                 break;
             } else {
                 sl.unlockRead(stamp);
                 stamp = sl.writeLock();
             }
         } } finally {
             sl.unlock(stamp);
         }
     }


    private static final Ad defaultAd = new Ad("默认广告");
    private static final ExecutorService service = Executors.newFixedThreadPool(10);


    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FechtAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep 期间被中断了");
                return new Ad("被中断的默认广告");
            }

            return new Ad("旅游订票哪家强，找某城");
        }
    }

    public void printAd() {
        Future<Ad> f = service.submit(new FechtAdTask());
        Ad ad;
        try {
            ad = f.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = f.cancel(true);
            System.out.println("cancel :" + cancel);
        }
        service.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        Timeout timeout = new Timeout();
        timeout.printAd();


    }
}
