package pl.training.chat.integration.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log
public class CacheExamples implements ApplicationRunner {

    private final Calculator calculator;

    @Override
    public void run(ApplicationArguments args) {
        log.info("First call result: " + calculator.add(1, 2));
        log.info("Second call result: " + calculator.add(1, 2));
    }

}
