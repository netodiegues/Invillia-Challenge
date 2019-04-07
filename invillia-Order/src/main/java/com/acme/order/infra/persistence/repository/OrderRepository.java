package com.acme.order.infra.persistence.repository;

import com.acme.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.diegues
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
