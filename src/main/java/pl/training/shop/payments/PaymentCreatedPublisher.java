package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Aspect
@Service
@RequiredArgsConstructor
public class PaymentCreatedPublisher {

    private final ApplicationEventPublisher publisher;

    @AfterReturning(value = "@annotation(pl.training.shop.commons.aspect.Loggable)", returning = "payment")
    public void onPaymentCreated(Payment payment) {
        publisher.publishEvent(payment);
    }

}
