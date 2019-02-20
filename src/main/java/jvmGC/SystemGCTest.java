package jvmGC;

/**
 * @author dingchenchen
 * @since 2019-02-20
 */
public class SystemGCTest {
    public static void main(String[] args) {

        //调用gc()方法，不一定能自动调用finalize()方法
        //jvm可能觉得垃圾不是很多，不需要清理
        for(int i=0;i<100;i++){
            new SystemGCTest();//创建了MyTest对面，但是没有引用指向该对象，引用指数为0
            System.gc();//gc()方法会自动调用对象的finalize()方法，此处是MyTest的finalize()方法
        }

    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("垃圾回收");
    }

}
