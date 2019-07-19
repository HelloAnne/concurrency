package com.anne.concurrency.example.controlThread;

public class NthreadLoopLock0719 implements Runnable{

    private static final Object LOCK = new Object();

    private int threadCount;
    private int maxNumber;
    private int currentNo;

    private static int currentCount=1;    // 注意static 共用。

    public NthreadLoopLock0719(int threadCount, int maxNumber, int currentNo) {
        this.threadCount = threadCount;
        this.maxNumber = maxNumber;
        this.currentNo = currentNo;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (LOCK) {
                while (currentCount % threadCount != currentNo) {
                    if (currentCount > maxNumber) {
                        break;
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
                if (currentCount > maxNumber) {
                    break;
                }
                System.out.println("thread " + Thread.currentThread().getName() + ": " + currentCount++);
                LOCK.notifyAll();
            }

        }
    }

    public static void main(String[] args) {
        int threadCount = 3;
        int maxNumber = 100;
        for (int i=0; i<threadCount; i++) {
            new Thread(new NthreadLoopLock0719(threadCount, maxNumber, i)).start();
        }
    }
}
