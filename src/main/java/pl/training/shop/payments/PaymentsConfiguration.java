package pl.training.shop.payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.payments.domain.*;
import pl.training.shop.payments.ports.PaymentRepository;
import pl.training.shop.payments.ports.TimeProvider;

// @Profile("dev")
@Configuration
public class PaymentsConfiguration {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PaymentProcessor paymentProcessor(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator,
                                             @Qualifier("jpaPaymentRepositoryAdapter") PaymentRepository paymentRepository,
                                             TimeProvider timeProvider) {
        return new PaymentProcessor(paymentIdGenerator, paymentFeeCalculator, paymentRepository, timeProvider);
    }

    @Bean
    public PaymentIdGenerator uuid() {
        return new UuidPaymentIdGenerator();
    }

    @Bean
    public PaymentFeeCalculator paymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }

}
