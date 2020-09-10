package jni;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author dingchenchen
 * @since 2020/9/10
 */
public interface Condition extends Serializable {

    void sayHello();

    default SerializedLambda getSerializedLambda() throws Exception {
        //writeReplace改了好像会报异常
        Method write = this.getClass().getDeclaredMethod("writeReplace");
        write.setAccessible(true);
        return (SerializedLambda) write.invoke(this);
    }
}
