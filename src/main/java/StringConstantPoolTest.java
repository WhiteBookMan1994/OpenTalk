import java.util.ArrayList;
import java.util.List;

/**
 * @author dxc
 * @date 2018/11/6 0006
 */
public class StringConstantPoolTest {
    public static void main (String []args) {
        //防止Full GC回收常量池
        List<String> list = new ArrayList<>();
        int i =0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
/*JDK7运行环境
* Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
* */

/* JDK9运行环境
jvm参数配置：-Xmx10m
E:\JDK9\bin\java -Xmx10m "-javaagent:E:\Intellij idea\lib\idea_rt.jar=53379:E:\Intellij idea\bin" -Dfile.encoding=UTF-8 -classpath F:\OpenTalk\target\classes StringConstantPoolTest
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.base/java.util.Arrays.copyOf(Arrays.java:3719)
	at java.base/java.util.Arrays.copyOf(Arrays.java:3688)
	at java.base/java.util.ArrayList.grow(ArrayList.java:237)
	at java.base/java.util.ArrayList.grow(ArrayList.java:242)
	at java.base/java.util.ArrayList.add(ArrayList.java:467)
	at java.base/java.util.ArrayList.add(ArrayList.java:480)
	at StringConstantPoolTest.main(StringConstantPoolTest.java:14)

Process finished with exit code 1
*/