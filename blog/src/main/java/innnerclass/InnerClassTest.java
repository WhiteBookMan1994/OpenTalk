package innnerclass;

public class InnerClassTest {
    private int i = 1;
    private static int j = 2;
    public InnerClass innerClass;
   class InnerClass {
       //static int k = 3; //编译报错,普通非静态内部类不能定义静态成员，除非是 final 常量
       void print(){
           System.out.println("i =" + i);
           System.out.println("j =" + j);
       }
   }

    public static void main(String[] args) {
        InnerClassTest test = new InnerClassTest();
        InnerClassTest.InnerClass innerClass = test.new InnerClass();
        innerClass.print();
    }
}
