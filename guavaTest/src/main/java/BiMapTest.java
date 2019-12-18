import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * key-value和value-key的双向map
 *
 * @author dingchenchen
 * @since 2019-12-18
 */
public class BiMapTest {

    public static void main(String[] args) {
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "one");
        biMap.put(2, "two");
        biMap.put(3, "three");
        biMap.put(1,"ONE");
        System.out.println(biMap.values());
        System.out.println(biMap.get(1));
        System.out.println(biMap.inverse().get("two"));
        System.out.println(biMap.inverse().get("one"));
        //把key映射到已经存在的值，直接用put方法会抛出异常，应该使用forcePut方法
        //biMap.put(4,"two");
        biMap.forcePut(4,"two");
        System.out.println(biMap);
    }
}
