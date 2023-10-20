package pl.training.chat.integration.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching//(order = 10_000)
@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager() {
        return new TransactionAwareCacheManagerProxy(new ConcurrentMapCacheManager("calculations"));
    }

}
