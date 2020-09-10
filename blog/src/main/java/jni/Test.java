package jni;

import java.lang.invoke.SerializedLambda;

/**
 * @author dingchenchen
 * @since 2020/9/10
 */
public class Test {

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("java.lang.String");
            System.out.println(clazz.getName()); // java.lang.String
            clazz = Class.forName("[Ljava.lang.String;");
            System.out.println(clazz.getName()); // [Ljava.lang.String;
            clazz = Class.forName("[D");
            System.out.println(clazz.getName()); // [D
            clazz = Class.forName("[I");
            System.out.println(clazz.getName()); // [I
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(Test[][][].class);

        Hello hello = new Hello(Test::test);
        try {
            SerializedLambda sl = hello.condition.getSerializedLambda();
            System.out.println(sl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test(){
        System.out.println("hello world");
    }
}
