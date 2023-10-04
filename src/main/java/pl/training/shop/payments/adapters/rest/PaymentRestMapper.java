package pl.training.shop.payments.adapters.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.commons.data.MoneyMapper;
import pl.training.shop.commons.data.ResultPage;
import pl.training.shop.commons.web.ResultPageDto;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.domain.PaymentRequest;

@Mapper(componentModel = "spring", uses = MoneyMapper.class)
public interface PaymentRestMapper {

    @Mapping(source = "requestId", target = "id")
    PaymentRequest toDomain(PaymentRequestDto paymentRequestDto);

    PaymentDto toDto(Payment payment);

    ResultPageDto<PaymentDto> toDto(ResultPage<Payment> paymentResultPage);

}
