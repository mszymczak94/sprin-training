package pl.training.chat.integration.cache;

import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Log
public class Calculator {

    @Cacheable("calculations")
    public int add(int a, int b) {
        log.info("Calculating sum value for %s and %s".formatted(a, b));
        return a + b;
    }

}
