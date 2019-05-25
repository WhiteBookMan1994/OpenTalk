package com.dxc.test.synchonized;

/**
 * 相同类的不同实例的同步方法测试
 *
 * @author dxc
 * @date 2019/5/21
 */
public class SynchronizedInstancesTest {

    private String name;

    SynchronizedInstancesTest(String name) {
        this.name = name;
    }

    /**
     * 同步方法
     */
    public synchronized void testMethod() {
        System.out.println(name + "的同步方法开始执行,startTime:" + System.currentTimeMillis());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "同步方法执行结束！endTime:" + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SynchronizedInstancesTest test1 = new SynchronizedInstancesTest("test1");
        SynchronizedInstancesTest test2 = new SynchronizedInstancesTest("test2");
        SynchronizedInstancesTest test3 = new SynchronizedInstancesTest("test3");
        new Thread(() -> test1.testMethod()).start();
        new Thread(() -> test2.testMethod()).start();
        new Thread(() -> test3.testMethod()).start();
    }
}
