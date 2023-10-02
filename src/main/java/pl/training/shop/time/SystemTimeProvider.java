package pl.training.shop.time;

import org.springframework.stereotype.Service;

import java.time.Instant;

// @Scope("singleton") // shared instance, default
// @Scope("prototype") // new instance per injection
@Service("timeProvider")
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
