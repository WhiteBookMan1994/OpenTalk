package interview;

/**
 * @author dingchenchen
 * @since 2020-05-15
 */
public class Producer implements Runnable{

    private Stack stack;

    private Integer i;

    public Producer(Stack stack, Integer i){
        this.stack = stack;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       stack.push(i);
        System.out.println(Thread.currentThread().getName()+"入栈：" + i);
        //System.out.println("入栈：" + i+"成功");
    }
}
