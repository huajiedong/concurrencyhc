package com.learn.cache;

import com.learn.cache.computable.Computable;
import com.learn.cache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/21
 */
public class Cache7<A,V> implements Computable<A,V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private  final Computable<A,V> c;
    public Cache7(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public  V compute(A arg) throws Exception {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> callable = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(callable);
            f = ft;
            cache.put(arg, ft);
            System.out.println("从FutureTask调用了计算函数");
            ft.run();
        }
        return f.get();
    }

    public static void main(String[] args) throws Exception {
        Cache7<String, Integer> expensiveComputer = new Cache7(new ExpensiveFunction());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("666");
                    System.out.println("第一次的计算结果："+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("667");
                    System.out.println("第二次的计算结果："+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer result = expensiveComputer.compute("666");
                    System.out.println("第三次的计算结果："+result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
