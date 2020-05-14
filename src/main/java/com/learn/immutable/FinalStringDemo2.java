package com.learn.immutable;

/**
 * @Author: HuaChenG
 * @Description:
 * @Date: Create in 2020/05/14
 */
public class FinalStringDemo2 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getDSX();
        String c = b + 2;
        System.out.println((a == c));
    }

    private static String getDSX() {
        return "wukong";
    }
}
