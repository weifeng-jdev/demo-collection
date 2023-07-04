package com.amano.shardingdemo;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @className: Test2
 * @package com.amano.shardingdemo
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/2
 **/
public class Test2 {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void print() {
        Thread thread = new Thread();
    }

    static class Task implements Runnable {
        private Thread t;
        private AtomicInteger count;

        @Override
        public void run() {
            while (count.get() <= 100) {
                System.out.println(count.get() % 2 == 0 ? "A" : "B");
            }
        }
    }
}
