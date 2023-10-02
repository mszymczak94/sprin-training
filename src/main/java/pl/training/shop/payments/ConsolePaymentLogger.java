package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(10_000)
@Aspect
@Component
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    @Pointcut("execution(* pl.training.shop.payments.*Processor.proc*(..))")
    // @Pointcut("execution(pl.training.shop.payments.Payment pl.training.shop.payments.PaymentProcessor.process(pl.training.shop.payments.PaymentRequest))")
    // @Pointcut("@annotation(pl.training.shop.commons.aspect.Loggable)")
    // @Pointcut("bean(paymentProcessor)")
    void process() {
    }

    @Before(value = "process() && args(paymentRequest)", argNames = "joinPoint,paymentRequest")
    public void beforeProcess(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        // var paymentRequest = (PaymentRequest) joinPoint.getArgs()[0];
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "process()", returning = "payment")
    public void onSuccess(Payment payment) {
        log.info("A new payment of %s has been initiated".formatted(payment.getValue()));
    }

    @AfterThrowing(value = "process()", throwing = "runtimeException")
    public void onFailure(JoinPoint joinPoint, RuntimeException runtimeException) {
        log.info("Payment processing failed with exception: %s (method: %s)"
                .formatted(runtimeException.getClass().getSimpleName(), joinPoint.getSignature()));
    }

    @After("process()")
    public void onFinish() {
        log.info("Payment processing complete");
    }

}
