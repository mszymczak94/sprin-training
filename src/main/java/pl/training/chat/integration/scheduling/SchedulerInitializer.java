package pl.training.chat.integration.scheduling;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class SchedulerInitializer implements ApplicationRunner {

    private final TaskScheduler customTaskScheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var task = new CheckStatusTask();
        customTaskScheduler.scheduleAtFixedRate(task::logStatus, Duration.ofMinutes(1));
        customTaskScheduler.schedule(task::logStatus, new CronTrigger("0 15 9-17 * * MON-FRI"));
    }

}
