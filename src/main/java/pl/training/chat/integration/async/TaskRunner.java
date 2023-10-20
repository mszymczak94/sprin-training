package pl.training.chat.integration.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log
public class TaskRunner implements ApplicationRunner {

    private final AsyncTask task;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var futureResult = task.run();
        log.info("Task is running");
        log.info("Is task completed: " + futureResult.isDone());
        log.info("Result : " + futureResult.get());
    }

}
