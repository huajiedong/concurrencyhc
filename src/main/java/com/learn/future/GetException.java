package com.learn.future;

import java.util.concurrent.*;

/**
 * @Author: HuaChenG
 * @Description: 演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机，并不是说一产生异常就抛出
 * 而是直到我们进行get时，才会抛出        get方法只会抛出ExecutionException异常
 * @Date: Create in 2020/05/20
 */
public class GetException {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Callable<Integer> callable = () -> {
            throw new IllegalArgumentException("callable 抛出异常");
        };
        Future<Integer> future = service.submit(callable);
        Thread.sleep(2000);
        System.out.println(future.isDone());
        try {
            future.get();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException 异常");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("ExecutionException 异常");
            e.printStackTrace();
        }
        service.shutdown();
    }
}
