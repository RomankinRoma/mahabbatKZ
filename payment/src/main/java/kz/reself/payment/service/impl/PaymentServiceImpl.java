package kz.reself.payment.service.impl;

import kz.reself.payment.model.Payment;
import kz.reself.payment.repository.PaymentRepository;
import kz.reself.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.saveAndFlush(payment);
    }

    @Override
    public void deletePaymentInfo(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment update(Payment payment) {
        return paymentRepository.saveAndFlush(payment);
    }

    @Override
    public List<Payment> getAllPaymentsIterable() {
        return paymentRepository.findAll();
    }

    @Override
    public Page<Payment> getAllPaymentsPageable(Map<String,String>params) {
        return paymentRepository.findAll(createDefaultPageableRequest(params));
    }

    @Override
    public List<Payment> getAllUserPaymentsIterable(Long userId) {
        return paymentRepository.getAllByUserId(userId);
    }

    @Override
    public Page<Payment> getAllUserPaymentsPageable(Map<String, String> params,Long id) {
            return paymentRepository.getAllByUserId(id,createDefaultPageableRequest(params));
    }

    private PageRequest createDefaultPageableRequest(Map<String,String> params){
        int page = 0;
        int size = 5;

        if (params.containsKey("page"))
            page= Integer.parseInt(params.get("page"));
        if (params.containsKey("size"))
            size= Integer.parseInt(params.get("size"));
        return PageRequest.of(page,size);
    }
}
