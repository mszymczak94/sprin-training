package pl.training.shop.payments.adapters.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, String>, JpaSpecificationExecutor<PaymentEntity>,
        JpaPaymentRepositoryExtensions { // CrudRepository<PaymentEntity, String> {

    Page<PaymentEntity> findByStatus(String status, Pageable pageable);

    // LOAD - All attributes specified in entity graph will be treated as Eager, and all attribute not specified will be treated as Lazy
    // FETCH - All attributes specified in entity graph will be treated as Eager, and all attribute not specified use their default/mapped value
    // @EntityGraph(PaymentEntity.WITH_PROPERTIES)
    @EntityGraph(attributePaths = "properties", type = LOAD)
    @Query("select p from Payment p where p.status = 'COMPLETED' and p.value >= :value")
    Page<PaymentEntity> findCompletedWithValue(/*@Param("value")*/ double value, Pageable pageable);

    @Query("select new pl.training.shop.payments.adapters.persistence.jpa.PaymentView(p.id, p.status) from Payment p")
    List<PaymentView> findAllAsPaymentView();

    @Query("select p.id as id, p.value as value, p.currencyCode as currencyCode from Payment p")
    Stream<PaymentProjection> findAllAsProjection();

    @Async
    @Query("select p from Payment p")
    Future<List<PaymentEntity>> findAllAsync();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Payment p set p.value = p.value + :fee")
    void applyFee(double fee);

}
