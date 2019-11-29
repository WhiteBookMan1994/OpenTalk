import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dingchenchen
 * @since 2019-11-28
 */
public class LuaTest {
    static final JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        pool = new JedisPool(config, "127.0.0.1");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> pool.close()));
    }

    static final String LUA_SCRIPT =
            "local sku_key = KEYS[1];local reduce_stock = ARGV[1];" +
                    "local current_num = tonumber(redis.call('HGET', sku_key, 'stock'));" +
                    "local remaining_num = current_num - tonumber(reduce_stock);" +
                    "if(remaining_num >= 0) then " +
                    "return redis.call('HSET', sku_key, 'stock', remaining_num) " +
                    "end return -1";

    public static Long deductStock(String skuId, Integer buyNum) {
        Jedis jedis = pool.getResource();
        try {
            return ((Long) jedis.eval(LUA_SCRIPT, Arrays.asList(skuId), Arrays.asList(buyNum.toString())));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void main(String args[]) {
        String skuId = "sku001";
        Integer buyNum = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        Map<String, Integer> userMap = new ConcurrentHashMap<>();
        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
        for (int i = 0; i < 5000; i++) {
            final String uid = "uid" + i;
            tasks.add(() -> {
                Long res = deductStock(skuId, buyNum);
                if (res == 0) {
                    userMap.put(uid, 1);
                }
                return res;
            });
        }
        try {
            executorService.invokeAll(tasks);
            //List<Future> futures = executorService.invokeAll(tasks);
            //futures.forEach();
            System.out.println(userMap.size());
            System.out.println(userMap);
            //executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
