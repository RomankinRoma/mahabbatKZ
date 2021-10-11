package kz.reself.payment.repository;

import kz.reself.payment.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> getAllByUserId(Long id);
    Page<Payment> getAllByUserId(Long id, Pageable pageable);
}
