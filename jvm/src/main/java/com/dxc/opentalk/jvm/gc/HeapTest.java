package com.dxc.opentalk.jvm.gc;

/**
 * java 堆 OutOfMemoryError测试
 *
 * @author dxc
 * @date 2018/11/4 0004
 */
public class HeapTest {

    public static void main(String []args){
        while (true){
           int a[] = new int[1024 * 1024 * 100];
        }
    }

/*
    public static void main(String []args){
        HashSet set = new HashSet();
        while (true){
            int a[] = new int[1024 * 1024 * 100];
            set.add(a);
        }
    }*/
}
/*
* jvm 参数配置：-XX:+UseSerialGC -XX:+PrintGCDetails
* */