import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * @author dingchenchen
 * @since 2019-12-18
 */
public class MultiSetTest {

    public static void main(String[] args) {
        Multiset<Integer> multiset = HashMultiset.create();
        multiset.add(1);
        multiset.add(2);
        multiset.add(1);
        multiset.add(3);
        multiset.add(1);
        multiset.add(3);
        System.out.println(multiset);
        System.out.println(multiset);
    }
}
