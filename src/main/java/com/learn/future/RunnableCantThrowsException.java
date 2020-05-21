package com.learn.future;

/**
 * @Author: HuaChenG
 * @Description: 在run方法中，无法抛出checked Exception
 * @Date: Create in 2020/05/20
 */
public class RunnableCantThrowsException extends Thread {

    @Override
    public void run() {
//        throw Exception();
    }
}
