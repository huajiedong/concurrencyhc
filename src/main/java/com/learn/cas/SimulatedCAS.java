package com.learn.cas;

/**
 * @Author: HuaChenG
 * @Description: 模拟CAS操作，等价代码
 * @Date: Create in 2020/05/13
 */
public class SimulatedCAS {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

}
