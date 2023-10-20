package pl.training.chat.integration.cache;

import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@CacheConfig(cacheNames = "calculations", keyGenerator = "simpleKeyGenerator")
@Component
@Log
public class Calculator {

    @Cacheable(/*value = "calculations", condition = "#a < #b", unless = ""*/)
    public int add(int a, int b) {
        log.info("Calculating sum value for %s and %s".formatted(a, b));
        return a + b;
    }

    @CacheEvict(/*cacheNames = "calculations",*/ allEntries = true)
    public void rest() {
        log.info("Cleaning the cache");
    }

    @CacheEvict(key = "#key")
    public void restResult(String key) {
        log.info("Cleaning entry with key: " + key);
    }

    @CachePut(key = "#key")
    public int addResult(String key, int value) {
        return value;
    }


}
