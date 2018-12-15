package ru.otus.l061.ehcache;


import org.ehcache.Cache;
import org.ehcache.CacheManager;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.stream.IntStream;


/**
 * @author sergey
 * created on 14.12.18.
 */
public class EhcacheDemo {
    private final CacheManager cacheManager;
    private final Cache<Integer, Long> cache;
    private static final Logger logger = LoggerFactory.getLogger(EhcacheDemo.class);

    public static void main(String[] args) {
        var demo = new EhcacheDemo();
    //    demo.withoutCache();
        demo.withCache();

        demo.close();
    }

    private void close() {
        this.cacheManager.close();
    }

    private EhcacheDemo() {
        this.cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(event ->
                                System.out.println("updated key:" + event.getKey() + ", value:" + event.getNewValue())
                        , EventType.CREATED, EventType.UPDATED)
                .ordered().synchronous();

        this.cache = cacheManager.createCache("Demo-Cache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Long.class,
                        ResourcePoolsBuilder.heap(5))
                        .add(cacheEventListenerConfiguration)
                        .build());

        logger.info("Cache setup is done");
    }

    private long getValue(int key) {
        Long value = cache.get(key);
        if (value == null) {
            value = SlowDataSrc.getValue(key);
            cache.put(key, value);
        }
        return value;
    }


    // без кеширования
    private void withoutCache() {
        System.out.println("Первое чтение");
        IntStream.range(1, 10).forEach(val -> System.out.println(SlowDataSrc.getValue(val)));
        System.out.println("Второе чтение");
        IntStream.range(1, 10).map(i -> 10 - i + 1 - 1).forEach(val -> System.out.println(SlowDataSrc.getValue(val)));
    }

    // с кешированием
    private void withCache() {
        System.out.println("Первое чтение");
        IntStream.range(1, 10).forEach(val -> System.out.println(this.getValue(val)));
        System.out.println("Второе чтение");
        IntStream.range(1, 10).map(i -> 10 - i + 1 - 1).forEach(val -> System.out.println(this.getValue(val)));

    }



}
