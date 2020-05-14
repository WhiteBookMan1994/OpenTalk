package DesignPattern;

/**
 * @author dingchenchen
 * @since 2020-04-19
 */
public class Proxy extends RealSubject{

    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    public void request1() {
        System.out.println("处理前准备工作...");
        realSubject.request();
        System.out.println("处理后执行工作...");
    }
}
