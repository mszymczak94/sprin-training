package pl.training.shop.payments.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.training.shop.payments.PaymentFixtures.TEST_MONEY_VALUE;

class PercentagePaymentFeeCalculatorTest {

    private static final double PERCENTAGE_VALUE = 0.1;

    private final PercentagePaymentFeeCalculator calculator = new PercentagePaymentFeeCalculator(PERCENTAGE_VALUE);

    @Test
    void given_payment_value_when_calculate_fee_then_returns_percentage_of_the_payment_value() {
        var fee = calculator.calculateFee(TEST_MONEY_VALUE);
        assertEquals(TEST_MONEY_VALUE.multiply(PERCENTAGE_VALUE), fee);
    }



}
