/**
 * @author dxc
 * @date 2018/11/4 0004
 */
public class StringInternTest {
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("ja") + new String("va");
        //String s3 = "1" + "1";
        s3.intern();
        String s4 = "java";
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
    }
}
