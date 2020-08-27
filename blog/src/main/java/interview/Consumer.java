package interview;

/**
 * @author dingchenchen
 * @since 2020-05-15
 */
public class Consumer implements Runnable{

    private Stack stack;

    public Consumer(Stack stack){
        this.stack = stack;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Integer i = (Integer) stack.pop();
            System.out.println(Thread.currentThread().getName()+"出栈："+ i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
