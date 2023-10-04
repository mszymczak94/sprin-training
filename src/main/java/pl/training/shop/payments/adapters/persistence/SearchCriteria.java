package pl.training.shop.payments.adapters.persistence;

import lombok.Value;

@Value
public class SearchCriteria {

    String propertyName;
    Object value;
    Matcher matcher;

    public enum Matcher {

        EQUAL, NOT_EQUAL, START_WITH

    }

}
