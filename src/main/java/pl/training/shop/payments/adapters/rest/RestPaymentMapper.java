package pl.training.shop.payments.adapters.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.payments.domain.PaymentRequest;

@Mapper(componentModel = "spring")
public interface RestPaymentMapper {

    @Mapping(source = "requestId", target = "id")
    PaymentRequest toDomain(PaymentRequestDto paymentRequestDto);

}
