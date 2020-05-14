package com.learn.immutable;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/14
 */
public class FinalStringDemo1 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }
}
