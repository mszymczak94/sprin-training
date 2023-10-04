package pl.training.shop.payments.adapters.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Transactional
@Component
@RequiredArgsConstructor
@Log
public class JpaExamples {

    private final JpaPaymentRepository repository;

    public void start() throws ExecutionException, InterruptedException {
        // log.info(repository.findByCurrencyCode(DEFAULT_CURRENCY_CODE).toString());
        // log.info(repository.findAllAsPaymentView().toString());
        //repository.findAllAsProjection()
        //        .forEach(row -> log.info(row.getId() + ":" + row.getValue()));

        // var result = repository.findAllAsync();
        // log.info("Is done: " + result.isDone());
        // log.info("Value: " + result.get());

        log.info(repository.findCompletedWithValue(10, PageRequest.of(0, 10)).getContent().toString());
    }

}
