package com.learn.cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/21
 */
public class Cache1 {
    private final HashMap<String, Integer> cache = new HashMap<>();

    public Integer computer(String key) throws InterruptedException {
        //先检查hashmap里面有没有保存过之前的计算结果
        Integer res = cache.get(key);
        if (res == null) {
            //如果缓存中找不到，那么需要现在计算一下结果，并且保存到hashmap中
            res = doComputer(key);
            cache.put(key, res);
        }
        return res;
    }
    private Integer doComputer(String key) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(key);
    }

    public static void main(String[] args) throws InterruptedException {
        Cache1 cache1 = new Cache1();
        System.out.println("开始计算");
        Integer res = cache1.computer("13");
        System.out.println("第一次计算结果" + res);
        res = cache1.computer("13");
        System.out.println("第二次计算结果" + res);


    }
}
