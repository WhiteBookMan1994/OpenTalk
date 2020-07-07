package innnerclass;

/**
 * @author dingchenchen
 * @since 2020/7/7
 */
public class StaticInnerClassTest {
    private int i = 1;
    private static int j = 2;

    static class StaticInnerClass {
        int k = 3;
        void print() {
            //System.out.println("i =" + i); 编译出错，静态内部类不能访问外部类非静态成员
            System.out.println("j =" + j);
            System.out.println("k =" + k);
        }
    }

    public static void main(String[] args) {
        StaticInnerClass staticInnerClass = new StaticInnerClass();
        staticInnerClass.print();
    }
}
