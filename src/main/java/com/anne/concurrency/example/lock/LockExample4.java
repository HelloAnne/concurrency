package com.anne.concurrency.example.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample4 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(()->{
            try {
                reentrantLock.lock();
                System.out.println("wait signal.");  //1
                condition.await(); // 加入condition队列，锁被释放
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get signal");  // 4
            reentrantLock.unlock();
        }).start();

        new Thread(()->{
            reentrantLock.lock();
            System.out.println("get lock.");  // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            System.out.println("send signal");  // 3
            reentrantLock.unlock();
        }).start();
    }
}
