package com.acme.order.domain.service;

import com.acme.order.domain.model.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.acme.order.infra.persistence.repository.OrderRepository;

/**
 *
 * @author jose.diegues
 */
@Service
public class OrderService implements ServiceInterface<Order, Long> {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<Order> findAllPagination(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Order create(Order model) {
        return orderRepository.save(model);
    }

    @Override
    public Order update(Order model) {
        return orderRepository.save(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
