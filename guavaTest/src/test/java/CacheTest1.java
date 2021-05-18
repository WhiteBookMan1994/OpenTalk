import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author dingchenchen
 * @since 2021/4/27
 */
public class CacheTest1 {

    public static void main(String[] args) throws ExecutionException {
        Cache<Integer, String> cache = CacheBuilder.newBuilder()
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(3)
                .build();
        cache.put(1,"one");
        cache.put(2,"two");
        cache.put(3,"three");
        System.out.println(cache.getIfPresent(1));
        System.out.println(cache.getIfPresent(22));
        cache.put(4,"four");
        cache.put(5,"five");
        System.out.println(cache.asMap());
        System.out.println(cache.size());
        System.out.println(cache.stats());
    }
}
