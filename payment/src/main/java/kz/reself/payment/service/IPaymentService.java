package kz.reself.payment.service;

import kz.reself.payment.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IPaymentService {
    Payment createPayment(Payment payment);
    void deletePaymentInfo(Long id);
    Payment update(Payment payment);
    List<Payment> getAllPaymentsIterable();
    Page<Payment> getAllPaymentsPageable(Map<String,String> params);
    List<Payment> getAllUserPaymentsIterable(Long userId);
    Page<Payment> getAllUserPaymentsPageable(Map<String,String> params,Long id);
}
