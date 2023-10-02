package pl.training.shop.payments;

import org.springframework.context.annotation.Primary;

import java.util.UUID;

@Primary
@Generator("uuid")
// @Component
public class UuidPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }

}
