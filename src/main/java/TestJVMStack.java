/**
 * @author dxc
 * @date 2018/11/6 0006
 */
public class TestJVMStack {
    private int count = 0;
    //没有出口的递归函数
    public void recursion(){
        //每次调用深度加1
        count ++;
        //递归
        recursion();
    }

    public void testStack(){
        try{
            recursion();
        } catch (Throwable e) {
            //打印栈溢出的深度
            System.out.println("deep of stack is " + count);
            e.printStackTrace();
        }
    }

    public static void main (String []args){
        TestJVMStack test = new TestJVMStack();
        test.testStack();
    }
}
