package com.anne.concurrency.example.aqs;

import java.util.concurrent.*;

public class CyclicBarrierExample3 {
    // 线程到达屏障时，优先执行runnable。
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->{
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("callback is running.");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        int threadCount = 10;
        for (int i=0; i<threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            exec.execute(()->{
                try {
                    race(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException, TimeoutException {
        Thread.sleep(1000);
        System.out.println("Thread "+ threadNum + " is ready");
        cyclicBarrier.await();
        System.out.println("Thread "+ threadNum + " continue.");
    }
}
