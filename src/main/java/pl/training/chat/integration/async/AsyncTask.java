package pl.training.chat.integration.async;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Component
@Log
public class AsyncTask {

    // @Async("tasksExecutor")
    @Async
    @SneakyThrows
    public Future<String> run() {
        Thread.sleep(10_000);
        log.info("Task if finished");
        return completedFuture("ok");
    }

}
