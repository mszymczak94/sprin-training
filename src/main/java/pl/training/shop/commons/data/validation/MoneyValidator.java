package pl.training.shop.commons.data.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.javamoney.moneta.FastMoney;

public class MoneyValidator implements ConstraintValidator<Money, String> {

    private double minValue;

    @Override
    public void initialize(Money constraintAnnotation) {
        minValue = constraintAnnotation.minValue();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        try {
            result = FastMoney.parse(value).isGreaterThanOrEqualTo(minValue);
        } catch (Exception exception) {
            result = false;
        }
        return result;
    }

}
