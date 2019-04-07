package com.acme.order.domain.service;

import com.acme.order.domain.model.OrderItem;
import com.acme.order.infra.persistence.repository.OrderItemRepository;
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
public class OrderItemService implements ServiceInterface<OrderItem, Long> {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository entityRepository) {
        this.orderItemRepository = entityRepository;
    }

    public Page<OrderItem> findAllPagination(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem findById(Long id) {
        return orderItemRepository.getOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return orderItemRepository.existsById(id);
    }

    @Override
    public OrderItem create(OrderItem model) {
        return orderItemRepository.save(model);
    }

    @Override
    public OrderItem update(OrderItem model) {
        return orderItemRepository.save(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<OrderItem> findByOrderId(Long id) {
        return this.orderItemRepository.findByOrderId(id);
    }

    public Page<OrderItem> findByOrderIdPagination(Long id, Pageable pageable) {
        return this.orderItemRepository.findByOrderId(id, pageable);
    }

    public OrderItem findByIdAndOrderId(Long orderId, Long itemId) {
        return this.orderItemRepository.findByOrderIdAndId(orderId, itemId);
    }

    public boolean existsByIdAndOrderId(Long itemId, Long orderId) {
        return this.orderItemRepository.existsByIdAndOrderId(itemId, orderId);
    }
}
