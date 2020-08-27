package waitandnotifytest;

/**
 * @author dingchenchen
 * @since 2020/8/8
 */
public class Test {
    static int i =1;
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread waitThread = new Thread(new WaitThread(lock,i), "waitThread");
        Thread notifyThread = new Thread(new NotifyThread(lock,i), "notifyThread");
        waitThread.start();
        Thread.sleep(1000);
        notifyThread.start();
    }
}
