package com.dxc.opentalk.jvm.gc;

/**
 * java对象逃脱回收实验
 * 来自周明耀《深入理解JVM&G1GC》
 *
 * @author dingchenchen
 * @since 2019-02-17
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive(){
        System.out.println("yes, I am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize() method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
     //   FinalizeEscapeGC SAVE_HOOK1 = new FinalizeEscapeGC();
     //   SAVE_HOOK1.toString();
        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead");
        }
        //下面这段代码与上面的完全相同，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead");
        }
    }

}

/*Run配置解决8：1问题
* -XX:+PrintGCDetails -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=8 -Xmn100m
* */