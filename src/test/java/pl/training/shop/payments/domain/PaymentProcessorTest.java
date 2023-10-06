package pl.training.shop.payments.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.training.shop.payments.ports.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.training.shop.payments.PaymentFixtures.*;

@ExtendWith(MockitoExtension.class)
class PaymentProcessorTest {

    private static final PaymentRequest PAYMENT_REQUEST = new PaymentRequest(1L, TEST_MONEY_VALUE);

    @Mock
    private PaymentRepository paymentRepository;
    private PaymentProcessor paymentProcessor;

    @BeforeEach
    void beforeEach() {
        when(paymentRepository.save(any(Payment.class))).then(returnsFirstArg());
        paymentProcessor = new PaymentProcessor(TEST_GENERATOR, TEST_FEE_CALCULATOR, paymentRepository, TEST_TIME_PROVIDER);
    }

    @Test
    void given_valid_payment_request_when_process_then_returns_payment() {
        assertEquals(TEST_PAYMENT, paymentProcessor.process(PAYMENT_REQUEST));
    }

    /*@Test
    void given_valid_payment_request_when_process_then_payment_is_persisted() {
        var payment = paymentProcessor.process(PAYMENT_REQUEST);
        verify(paymentRepository).save(payment);
    }*/

}
