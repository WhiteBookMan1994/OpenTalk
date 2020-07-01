import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author dingchenchen
 * @since 2020-05-27
 */
public class Test {

    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>(14,.75f);
        hashMap.put(1, "one");
        hashMap.put(15, "fifteen");
        hashMap.put(17, "seventeen");//注意 17 和 1 有 hash 碰撞
        hashMap.put(3, "three");
        hashMap.put(2, "two");
        System.out.println(hashMap);

        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>(14,.75f);
        linkedHashMap.put(1, "one");
        linkedHashMap.put(15, "fifteen");
        linkedHashMap.put(17, "seventeen");
        linkedHashMap.put(3, "three");
        linkedHashMap.put(2, "two");
        System.out.println(linkedHashMap);
    }
}
