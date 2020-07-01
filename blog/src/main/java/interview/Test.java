package interview;

/**
 * @author dingchenchen
 * @since 2020-05-15
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Stack  stack = new Stack();
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(new Producer(stack, i),"入栈线程"+i);
            thread.start();
        }
        for (int i = 1; i <= 20; i++) {
            Thread thread = new Thread(new Consumer(stack), "出栈线程"+i);
            thread.start();
        }
    }
}
