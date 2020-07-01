package DesignPattern;

/**
 * @author dingchenchen
 * @since 2020-04-19
 */
public class Test {
    public static void main(String[] args) {
        RealSubject subject = new Proxy(new RealSubject());
        subject.request();
    }
}
