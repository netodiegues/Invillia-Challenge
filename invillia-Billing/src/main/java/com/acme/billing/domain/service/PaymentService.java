package com.acme.billing.domain.service;

import com.acme.billing.domain.model.Payment;
import com.acme.billing.infra.persistence.repository.PaymentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose.diegues
 */
@Service
public class PaymentService implements ServiceInterface<Payment, Long> {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Page<Payment> findAllPagination(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.getOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return paymentRepository.existsById(id);
    }

    @Override
    public Payment create(Payment model) {
        return paymentRepository.save(model);
    }

    @Override
    public Payment update(Payment model) {
        return paymentRepository.save(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
