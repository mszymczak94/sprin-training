package pl.training.shop;

import org.springframework.context.annotation.*;
import pl.training.shop.time.SystemTimeProvider;
import pl.training.shop.time.TimeProvider;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ApplicationConfiguration {

    @Scope("singleton") // default
    @Bean(name = {"timeProvider", "systemTimeProvider"})
    public TimeProvider timeProvider() {
        return new SystemTimeProvider();
    }

}
