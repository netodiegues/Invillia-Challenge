package com.acme.billing.application.controller;

import com.acme.billing.application.exception.ResourceNotFoundException;
import com.acme.billing.domain.model.Payment;
import com.acme.billing.domain.service.PaymentService;
import com.acme.billing.infra.util.RestResponsePage;
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
@RequestMapping(value = "payments")
@CrossOrigin(value = "*")
public class PaymentController {
    
    private final PaymentService paymentService;
    
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<RestResponsePage<?>> findAll(Pageable pageable) {
        Page<?> requestPage = this.paymentService.findAllPagination(pageable);
        RestResponsePage<?> result = new RestResponsePage<>(requestPage.getContent(), requestPage.getTotalPages());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        if (this.paymentService.existsById(id)) {
            return new ResponseEntity<>(this.paymentService.findById(id), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Payment not found for ID: " + id);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@Valid @RequestBody Payment model, @PathVariable("storeId") Long storeId) {
        model.setId(null);
        model.setStoreId(storeId);
        return new ResponseEntity<>(this.paymentService.create(model), HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Payment model, @PathVariable Long id) {
        if ((Objects.equals(id, model.getId())) && (this.paymentService.existsById(id))) {
            return new ResponseEntity<>(this.paymentService.update(model), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Payment not exists for ID: " + id);
        }
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (this.paymentService.existsById(id)) {
            return new ResponseEntity<>(this.paymentService.deleteById(id), HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("Payment not exists for ID: " + id);
        }
    }
}
