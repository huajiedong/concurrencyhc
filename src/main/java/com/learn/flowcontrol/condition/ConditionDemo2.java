package com.learn.flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: HuaChenG
 * @Description: 演示用Condition实现生产者消费者模式
 * @Date: Create in 2020/05/19
 */
public class ConditionDemo2 {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    class Consumer extends Thread {
        @Override
        public void run() {
            consumer();
        }

        private void consumer() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        System.out.println("队列已空，等待数据");
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    System.out.println("从队列里取走一个数据，队列剩余：" + queue.size());
                    notFull.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            producer();
        }

        private void producer() {
            while (true) {
                lock.lock();
                try {

                    while (queue.size() == queueSize) {
                        System.out.println("队列已满，等待消费");
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    System.out.println("添加一个数据到队列，队列剩余：" + (queueSize - queue.size()));
                    notEmpty.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo2 demo2  = new ConditionDemo2();
        Consumer consumer = demo2.new Consumer();
        Producer producer = demo2.new Producer();
        consumer.start();
        producer.start();

    }

}
