package com.acme.store.application.controller;

import com.acme.store.application.exception.ResourceNotFoundException;
import com.acme.store.domain.model.Store;
import com.acme.store.domain.service.StoreService;
import com.acme.store.infra.util.RestResponsePage;
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
@RequestMapping(value = "stores")
@CrossOrigin(value = "*")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService service) {
        this.storeService = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<RestResponsePage<?>> findAll(Pageable pageable) {
        Page<?> requestPage = this.storeService.findAllPagination(pageable);
        RestResponsePage<?> result = new RestResponsePage<>(requestPage.getContent(), requestPage.getTotalPages());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        if (this.storeService.existsById(id)) {
            return new ResponseEntity<>(this.storeService.findById(id), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Store not found for ID: " + id);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@Valid @RequestBody Store model) {
        model.setId(null);
        return new ResponseEntity<>(this.storeService.create(model), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Store model, @PathVariable Long id) {
        if ((Objects.equals(id, model.getId())) && (this.storeService.existsById(id))) {
            return new ResponseEntity<>(this.storeService.update(model), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Store not exists for ID: " + id);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (this.storeService.existsById(id)) {
            return new ResponseEntity<>(this.storeService.deleteById(id), HttpStatus.NO_CONTENT);
        } else {
            throw new ResourceNotFoundException("Store not exists for ID: " + id);
        }
    }
}
