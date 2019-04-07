package com.acme.order.application.controller;

import com.acme.order.application.exception.ResourceNotFoundException;
import com.acme.order.domain.model.Order;
import com.acme.order.domain.service.OrderService;
import com.acme.order.infra.util.RestResponsePage;
import java.util.Objects;
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
@RequestMapping(value = "orders")
@CrossOrigin(value = "*")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<RestResponsePage<?>> findAll(Pageable pageable) {
        Page<?> requestPage = this.orderService.findAllPagination(pageable);
        RestResponsePage<?> result = new RestResponsePage<>(requestPage.getContent(), requestPage.getTotalPages());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        if (this.orderService.existsById(id)) {
            return new ResponseEntity<>(this.orderService.findById(id), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Order not found for ID: " + id);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@Valid @RequestBody Order model) {
        model.setId(null);
        return new ResponseEntity<>(this.orderService.create(model), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Order model, @PathVariable Long id) {
        if ((Objects.equals(id, model.getId())) && (this.orderService.existsById(id))) {
            return new ResponseEntity<>(this.orderService.update(model), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Order not exists for ID: " + id);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (this.orderService.existsById(id)) {
            return new ResponseEntity<>(this.orderService.deleteById(id), HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("Order not exists for ID: " + id);
        }
    }
}
