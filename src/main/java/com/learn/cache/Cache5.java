package com.learn.cache;

import com.learn.cache.computable.Computable;
import com.learn.cache.computable.ExpensiveFunction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/21
 */
public class Cache5<A,V> implements Computable<A,V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>();

    private  final Computable<A,V> c;
    public Cache5(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public  V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
                cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Cache5<String, Integer> expensiveComputer = new Cache5(new ExpensiveFunction());
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
                    Integer result = expensiveComputer.compute("666");
                    System.out.println("第三次的计算结果："+result);
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

    }

}
