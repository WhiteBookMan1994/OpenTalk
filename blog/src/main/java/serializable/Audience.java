package serializable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * @author dingchenchen
 * @since 2020/9/21
 */
public abstract class Audience<R> {
    public static final NumberFormat NF = new DecimalFormat("0000");

    public R player;

    public Condition<?> condition;

    public <C> void setCondition(Condition<C> condition) {
        this.condition = condition;
    }

    public void applaud(Condition<R> condition) {
        if (condition.isSatisfied(player)) {
            System.out.println("Audience applaud ...");
        }
    }

    public static void main(String[] args) {
        int i = 1;
        System.out.println(test(i));
    }

    public static int test(int i) {
        try {
            i = i + 1;
            System.out.println("i =" + i);
            return i;
        } catch (Exception e) {
            i = -1;
            return i;
        } finally {
            System.out.println("i =" + i);
            i = 0;
            System.out.println("i =" + i);
            //return i;
        }
    }
}
