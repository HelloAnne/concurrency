package com.anne.concurrency.example.aqs;

import java.sql.Time;
import java.util.concurrent.*;

public class CyclicBarrierExample2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

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
        cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        System.out.println("Thread "+ threadNum + " continue.");
    }
}
