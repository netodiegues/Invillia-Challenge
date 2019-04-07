package com.acme.order.infra.persistence.repository;

import com.acme.order.domain.model.OrderItem;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.diegues
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long id);

    Page<OrderItem> findByOrderId(Long id, Pageable pageable);

    OrderItem findByOrderIdAndId(Long orderId, Long itemId);

    boolean existsByIdAndOrderId(Long itemId, Long orderId);
}
