package com.huirong.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * Created by huirong on 17-5-21.
 */
public class Test {
    public static final int NUM = 10;
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(NUM);
        Thread[] threads = new Thread[NUM];
        for (int i = 0; i < NUM; i++){
            threads[i] = new Thread(new Worker(latch));
            threads[i].start();
        }
    }
}
