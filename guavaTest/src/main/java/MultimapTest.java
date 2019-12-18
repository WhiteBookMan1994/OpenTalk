import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * @author dingchenchen
 * @since 2019-12-16
 */
public class MultimapTest {

    public static void main(String[] args) {
        Multimap<Integer,Integer> multimap = ArrayListMultimap.create();
        multimap.put(1,1);
        multimap.put(1,2);
        multimap.put(1,3);
        multimap.put(2,2);
        multimap.put(3,3);
        //除了两个不可变形式的实现（ImmutableListMultimap、ImmutableSetMultimap），其他所有实现都支持null键和null值
        multimap.put(null,null);
        multimap.put(null,6);
        System.out.println(multimap);
        System.out.println(multimap.get(1));
        //需要注意Multimap中不存在的key，会返回一个空List
        System.out.println(multimap.get(12).isEmpty());
    }
}
