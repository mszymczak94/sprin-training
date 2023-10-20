package pl.training.chat.integration.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log
public class CacheExamples implements ApplicationRunner {

    private final Calculator calculator;
    private final CacheManager cacheManager;

    @Override
    public void run(ApplicationArguments args) {
        log.info("First call result: " + calculator.add(1, 2));
        log.info("Second call result: " + calculator.add(1, 2));
        calculator.rest();
        log.info("Third call result: " + calculator.add(1, 2));
        calculator.restResult("add[1, 2]");
        calculator.addResult("add[1, 2]", 9);
        log.info("Fourth call result: " + calculator.add(1, 2));
        Optional.ofNullable(cacheManager.getCache("calculations"))
                .ifPresent(Cache::clear);
        log.info("Fifth call result: " + calculator.add(1, 2));
    }

}
