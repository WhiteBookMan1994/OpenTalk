/**
 * @author dxc
 * @date 2018/11/5 0005
 */
public class GetJVMInfoTest {
    public static void main(String []args) {
        Runtime runtime = Runtime.getRuntime();
        int freeMemory = (int) (runtime.freeMemory() / 1024 / 1024);
        int totalMemory = (int) (runtime.totalMemory() / 1024 / 1024);
        System.out.println("memory info :" + freeMemory + "M/" + totalMemory + "M(free/total)");
    }
}
