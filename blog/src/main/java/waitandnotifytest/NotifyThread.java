package waitandnotifytest;


/**
 * @author dingchenchen
 * @since 2020/8/8
 */
public class NotifyThread implements Runnable {

    Object lock;
    int i;

    public NotifyThread(Object lock, int i) {
        this.lock = lock;
        this.i = i;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        while (true) {
            synchronized (lock) {
                Thread t1 = Thread.currentThread();
                System.out.println(t1.getName() + ":" + (i++));
                if (i > 101) {//设置中断数字
                    lock.notify();
                    break;
                }
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
