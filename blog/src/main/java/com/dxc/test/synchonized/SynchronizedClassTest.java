package com.dxc.test.synchonized;

/**
 * synchronized修饰类或者类的static方法
 *
 * @author dxc
 * @date 2019/5/25
 */
public class SynchronizedClassTest {

    private String name;

    SynchronizedClassTest(String name) {
        this.name = name;
    }

    /**
     * synchronized修饰static方法
     * */
    public synchronized static void test(SynchronizedClassTest p) {
        System.out.println(p.name + "的同步方法开始执行,startTime:" + System.currentTimeMillis());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(p.name + "同步方法执行结束！endTime:" + System.currentTimeMillis());
    }

    /**
     * synchronized修饰static方法2
     * */
    public synchronized static void testStatic(SynchronizedClassTest p) {
        System.out.println(p.name + "的同步方法2开始执行,startTime:" + System.currentTimeMillis());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(p.name + "同步方法2执行结束！endTime:" + System.currentTimeMillis());
    }

    /**
     * synchronized修饰非static方法
     * */
    public synchronized void testNotStatic() {
        System.out.println(name + "的同步方法开始执行,startTime:" + System.currentTimeMillis());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "同步方法执行结束！endTime:" + System.currentTimeMillis());
    }

    public static void main(String []args){
        SynchronizedClassTest test1 = new SynchronizedClassTest("test1");
        SynchronizedClassTest test2 = new SynchronizedClassTest("test2");
        SynchronizedClassTest test3 = new SynchronizedClassTest("test3");
        new Thread(() -> SynchronizedClassTest.test(test1)).start();
        //new Thread(() -> SynchronizedClassTest.test(test2)).start();
        //new Thread(() -> test2.testNotStatic()).start();
        new Thread(() -> SynchronizedClassTest.testStatic(test2)).start();
        new Thread(() -> SynchronizedClassTest.test(test3)).start();
    }
}
