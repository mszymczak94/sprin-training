package pl.training.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.Money;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.commons.data.Page;
import pl.training.shop.payments.adapters.persistence.JpaExamples;
import pl.training.shop.payments.domain.PaymentRequest;
import pl.training.shop.payments.ports.PaymentService;

import java.util.concurrent.ExecutionException;

import static pl.training.shop.payments.domain.PaymentStatus.STARTED;

@Log
public class Application {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Application().start();
    }

    public void start() throws ExecutionException, InterruptedException {
        try (var container = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            var paymentService = container.getBean(PaymentService.class);
            var paymentRequest = new PaymentRequest(1L, Money.of(1_000, DEFAULT_CURRENCY_CODE));
            var payment = paymentService.process(paymentRequest);
            var id = payment.getId();
            log.info(paymentService.getById(id).toString());
            log.info(paymentService.getByStatus(STARTED, new Page(0, 10)).toString());

            container.getBean(JpaExamples.class).start();
        }
    }

}
