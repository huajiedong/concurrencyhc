package com.learn.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: HuaChenG
 * @Description: 批量提交任务时，用list来批量接收结果
 * @Date: Create in 2020/05/20
 */
public class MultiFutures {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ArrayList<Future> futures = new ArrayList<>();
        Callable<Integer> callable = () -> {
            Thread.sleep(2000);
            return new Random().nextInt();
        };
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = service.submit(callable);
            futures.add(future);
        }
        for (int i = 0; i < 20; i++) {
            try {
                System.out.println("futures.get(i).get() = " + futures.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
}
