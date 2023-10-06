package pl.training.shop.payments.adapters.persistence.jpa;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static pl.training.shop.payments.PaymentFixtures.createEntity;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = NONE)
@DataJpaTest
@ExtendWith(SpringExtension.class)
class JpaPaymentRepositoryTest {

    @Autowired
    private JpaPaymentRepository repository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void given_completed_payment_in_database_when_find_by_status_and_value_then_returns_the_payment() {
        var paymentEntity = initDatabase("COMPLETED");
        var result = repository.findCompletedWithValue(paymentEntity.getValue(), PageRequest.of(0, 1));
        assertFalse(result.getContent().isEmpty());
    }

    @Test
    void given_started_payment_in_database_when_find_by_status_and_value_then_returns_empty_list() {
        var paymentEntity = initDatabase("STARTED");
        var result = repository.findCompletedWithValue(paymentEntity.getValue(), PageRequest.of(0, 1));
        assertTrue(result.getContent().isEmpty());
    }

    private PaymentEntity initDatabase(String status) {
        var paymentEntity = createEntity(status);
        entityManager.persist(paymentEntity);
        entityManager.flush();
        return paymentEntity;
    }

}
