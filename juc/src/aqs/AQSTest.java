package aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AQSTest {

    static final MiniReentrantLock lock = new MiniReentrantLock();
    final static ReentrantLock reentrantLock = new ReentrantLock();

    public static void test() {
        //        testMiniReentrantLock();
//        testAcquired();
//        Thread.currentThread().interrupt();
//        Thread.interrupted()

//       Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testInterrupt("thread1");
//            }
//        });
//
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testInterrupt("thread2");
//            }
//        });
//
//        thread1.start();
//        thread2.start();
//        System.out.println("中断了");
//
        JUCUtilTest.test();
    }

    private static void testReadWriteLock() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        readLock.lock();
        readLock.unlock();

        writeLock.lock();
        writeLock.unlock();


    }

    public static void testInterrupt(String sleep) {

        try {
            //                System.out.println("park after");

            reentrantLock.lock();
//            reentrantLock.lock();
//            System.out.println("before park" );
//            LockSupport.park();
//            System.out.println("park after");
            int a = 1;
            System.out.println("执行");

            for (int i = 0; i< Integer.MAX_VALUE/1000 - 1 ; i++) {
                        a = a *100;
            }
//            if (sleep > 0) {
//                System.out.println("before park" );
//                LockSupport.park();
//                System.out.println("park after");
//            }
        }
//        catch (InterruptedException e) {
//            System.out.println("InterruptedException" + sleep + e);
//        }
        finally {
            System.out.println("unlock");
            reentrantLock.unlock();
        }
    }

    public static void testAcquired() {
        boolean failed = true;
        try {
//            for (; ; ) {

                System.out.println("park");
//
            throw new Error("make error");
//                LockSupport.park();
//            }
        } finally {
            System.out.println("finally");
        }
    }

    public static void testMiniReentrantLock() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    sync("thread 1");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sync("thread 2");
            }
        }).start();
    }

    private static void sync(String s) {
        lock.lock();
        try {
            System.out.println(s);
        } finally {
            lock.unlock();
        }
    }
}
