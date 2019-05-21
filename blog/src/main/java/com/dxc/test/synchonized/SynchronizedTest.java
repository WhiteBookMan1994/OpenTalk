package com.dxc.test.synchonized;

/**
 * synchronized关键字锁对象测试
 *
 * @author dxc
 * @date 2019/5/21
 */
public class SynchronizedTest {

    /**
     * synchronized修饰非static成员方法
     * */
    public synchronized void test(){
        System.out.println("线程3同步块开始执行：startTime3：" + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        new Thread(() -> {
            synchronized (test) {
                System.out.println("线程1同步块开始执行：startTime1：" + System.currentTimeMillis());
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程1结束执行：endTime1：" + System.currentTimeMillis());
        }).start();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (test) {
                System.out.println("线程2同步块开始执行：startTime2：" + System.currentTimeMillis());
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程2结束执行：endTime2：" + System.currentTimeMillis());
        }).start();
        new Thread(() -> {
            test.test();
            System.out.println("线程3结束执行：endTime3：" + System.currentTimeMillis());
        }).start();
    }
}
