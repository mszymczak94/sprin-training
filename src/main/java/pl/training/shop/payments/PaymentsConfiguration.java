package pl.training.shop.payments;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.training.shop.commons.aspect.CacheAspect;
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

    /*@Bean
    public Advisor cacheAdvisor(CacheAspect cacheAspect) {
        var pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(pl.training.shop.payments.Payment pl.training.shop.payments.PaymentProcessor.getById(String))");
        // Alternatively instead of pointcut one can implement custom ExpressionPointcut
        return new DefaultPointcutAdvisor(pointcut, cacheAspect);
    }*/

}
