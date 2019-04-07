package com.acme.billing.infra.persistence.repository;

import com.acme.billing.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.diegues
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
