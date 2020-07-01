package superandextends;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dingchenchen
 * @since 2020-06-30
 */
public class Plate<T> {
    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    /**
     * ﻿1) 参数写成：T<? super B>，对于这个泛型，?代表容器里的元素类型，由于只规定了元素必须是B的超类，导致元素没有明确统一的“根”（除了Object这个必然的根），
     *    所以这个泛型你其实无法使用它，对吧，除了把元素强制转成Object。所以，对把参数写成这样形态的函数，你函数体内，只能对这个泛型做插入操作，而无法读
     * <p>
     * 2) 参数写成： T<? extends B>，由于指定了B为所有元素的“根”，你任何时候都可以安全的用B来使用容器里的元素，但是插入有问题，
     *    由于供奉B为祖先的子树有很多，不同子树并不兼容，由于实参可能来自于任何一颗子树，所以你的插入很可能破坏函数实参，所以，对这种写法的形参，禁止做插入操作，只做读取
     *
     * 注意：T<? extends B> 数据插入来自其它地方，不会用它直接插入数据
     */

    public static void main(String[] args) {
        // compile error
        // List <? extends Fruit> appList2 = new ArrayList();
        // appList2.add(new Fruit());
        // appList2.add(new Apple());
        // appList2.add(new Pear());

        List appList2 = new ArrayList();
        appList2.add(new Fruit());
        appList2.add(new Apple());
        appList2.add(new Pear());

        List<? super Fruit> appList = new ArrayList();
        appList.add(new Fruit());
        appList.add(new Apple());
        appList.add(new Pear());

        List<? extends Fruit> appList3 = Arrays.asList(new Apple(), new Pear(), new Apple());
        appList3.forEach(System.out::println);
    }
}
