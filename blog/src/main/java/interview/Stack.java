package interview;

import java.util.LinkedList;

/**
 * @author dingchenchen
 * @since 2020-05-15
 */
public class Stack {

    LinkedList list = new LinkedList();
    public synchronized void push(Object x) {
        synchronized(list) {
            System.out.println("hello world");
            list.addLast( x );
            list.notify();
            this.notify();
        }
    }

    public synchronized Object pop()
            throws Exception {
        synchronized(list) {
            if( list.size() <= 0 ) {
                list.wait();
                this.wait();
            }
            return list.removeLast();
        }
    }

}
