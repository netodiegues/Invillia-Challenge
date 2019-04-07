package com.acme.order.application.controller;

import com.acme.order.application.exception.ResourceNotFoundException;
import com.acme.order.domain.model.OrderItem;
import com.acme.order.domain.service.OrderItemService;
import com.acme.order.domain.service.OrderService;
import com.acme.order.infra.util.RestResponsePage;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jose.diegues
 */
@RestController
@RequestMapping(value = "orders/{orderId}/items")
@CrossOrigin(value = "*")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, OrderService orderService) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> findAll(@PathVariable("orderId") Long orderId, Pageable pageable) {
        Page<?> requestPage = orderItemService.findByOrderIdPagination(orderId, pageable);
        RestResponsePage<?> result = new RestResponsePage<>(requestPage.getContent(), requestPage.getTotalPages());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{orderItemId}")
    public ResponseEntity<?> findById(@PathVariable("orderId") Long orderId, @PathVariable("orderItemId") Long orderItemId) {
        return new ResponseEntity<>(this.orderItemService.findByIdAndOrderId(orderId, orderItemId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@Valid @RequestBody OrderItem model, @PathVariable("orderId") Long orderId) {
        if (orderService.existsById(orderId)) {
            model.setOrder(orderService.findById(orderId));
            return new ResponseEntity<>(this.orderItemService.create(model), HttpStatus.CREATED);
        } else {
            throw new ResourceNotFoundException("Order does not exists for ID: " + orderId);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{orderItemId}")
    public ResponseEntity<?> update(@Valid @RequestBody OrderItem model, @PathVariable("orderId") Long orderId, @PathVariable("orderItemId") Long orderItemId) {
        if (this.orderItemService.existsByIdAndOrderId(orderItemId, orderId)) {
            model.setOrder(orderService.findById(orderId));
            return new ResponseEntity<>(this.orderItemService.update(model), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Item does not exists for ID " + orderItemId + " and Order " + orderId);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{orderItemId}")
    public ResponseEntity<?> deleteById(@PathVariable("orderId") Long orderId, @PathVariable("orderItemId") Long orderItemId) {
        if (this.orderItemService.existsByIdAndOrderId(orderItemId, orderId)) {
            return new ResponseEntity<>(this.orderItemService.deleteById(orderItemId), HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("Item does not exists for ID " + orderItemId + " and Order " + orderId);
        }
    }
}
