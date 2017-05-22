package com.huirong.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * Created by huirong on 17-5-21.
 */
public class Worker implements Runnable {

    CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run(){
        latch.countDown();
        try {
            Thread.sleep(10000);
            System.out.println(Singleton.getInstance().hashCode());
//            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
