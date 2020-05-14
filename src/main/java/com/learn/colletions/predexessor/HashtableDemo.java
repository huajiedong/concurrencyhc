package com.learn.colletions.predexessor;

import java.util.Hashtable;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/14
 */
public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("薪资涨幅", "50%");
        System.out.println(hashtable.get("薪资涨幅"));
    }
}
