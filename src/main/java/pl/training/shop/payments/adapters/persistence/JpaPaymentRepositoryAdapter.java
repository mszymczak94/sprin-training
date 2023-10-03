package pl.training.shop.payments.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.ports.PaymentRepository;

import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Component
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepositoryMapper mapper;
    private final JpaPaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        var paymentEntity = mapper.toEntity(payment);
        return mapper.toDomain(repository.save(paymentEntity));
    }

    @Override
    public Optional<Payment> getById(String id) {
        return repository.getById(id)
                .map(mapper::toDomain);
    }

}
