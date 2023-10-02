package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.training.shop.time.TimeProvider;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

// @Scope(SCOPE_PROTOTYPE) - for prototypes destroy method is not invoked
@Service
@Log
public class PaymentProcessor implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Autowired // annotation is not required but help to resolve conflicts with many constructors
    public PaymentProcessor(// @Qualifier("uuidPaymentIdGenerator") PaymentIdGenerator paymentIdGenerator, // qualification with annotation
                            // PaymentIdGenerator uuidPaymentIdGenerator,                                  // qualification by argument/bean name
                            @Generator("fake") PaymentIdGenerator paymentIdGenerator,                      // qualification by custom annotation
                            PaymentFeeCalculator paymentFeeCalculator,
                            PaymentRepository paymentsRepository, TimeProvider timeProvider) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.paymentFeeCalculator = paymentFeeCalculator;
        this.paymentsRepository = paymentsRepository;
        this.timeProvider = timeProvider;
    }

    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentValue = calculatePaymentValue(paymentRequest.getValue());
        var payment = createPayment(paymentValue);
        return paymentsRepository.save(payment);
    }

    private Payment createPayment(Money paymentValue) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(timeProvider.getTimestamp())
                .status(PaymentStatus.STARTED)
                .build();
    }

    private Money calculatePaymentValue(Money paymentValue) {
        var paymentFee = paymentFeeCalculator.calculateFee(paymentValue);
        return paymentValue.add(paymentFee);
    }

    @PostConstruct
    public void init() {
        log.info("Initializing Payment processor");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying Payment processor");
    }

}
