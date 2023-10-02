package pl.training.shop.payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.training.shop.time.TimeProvider;

@Configuration
public class PaymentsConfiguration {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PaymentProcessor paymentProcessor(// @Qualifier("uuid") PaymentIdGenerator paymentIdGenerator,
                                             // PaymentIdGenerator fake,
                                             PaymentIdGenerator paymentIdGenerator,
                                             TimeProvider timeProvider) {
        return new PaymentProcessor(paymentIdGenerator /*uuid()*/, paymentFeeCalculator(), paymentRepository(), timeProvider); // spring respects factories scopes
    }

    @Primary
    @Bean
    public PaymentIdGenerator uuid() {
        return new UuidPaymentIdGenerator();
    }

    @Bean
    public PaymentIdGenerator fake() {
        return new FakePaymentIdGenerator();
    }

    @Bean
    public PaymentFeeCalculator paymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new InMemoryPaymentRepository();
    }

}
