package pl.training.chat.integration.scheduling;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Log
public class CheckStatusTask {

    // @Scheduled(cron = "@daily")
    // @Scheduled(cron = "0 */1 * * * *")
    // @Scheduled(fixedRate = 5_000)
    public void logStatus() {
        log.info("Current status: ok (%s)".formatted(Instant.now()));
    }

}
