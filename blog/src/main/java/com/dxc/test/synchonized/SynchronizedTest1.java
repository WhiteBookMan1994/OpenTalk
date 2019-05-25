package com.dxc.test.synchonized;

/**
 * synchronized修饰
 *
 * @author dxc
 * @date 2019/5/26
 */
public class SynchronizedTest1 {

     /**
      * synchronized修饰非static成员方法
      * */
     public synchronized void test(){
          System.out.println("线程3同步块开始执行：startTime3：" + System.currentTimeMillis());
     }

     public static void main(String args[]){
          //同步代码块
         synchronized (SynchronizedTest1.class){

         }
         //同步方法
          new SynchronizedTest1().test();
     }
}
