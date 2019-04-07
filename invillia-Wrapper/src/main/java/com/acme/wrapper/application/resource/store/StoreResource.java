package com.acme.wrapper.application.resource.store;

import com.acme.wrapper.application.exception.ValidationErrorException;
import com.acme.wrapper.domain.model.store.Store;
import com.acme.wrapper.domain.service.store.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "wrapper/stores")
@CrossOrigin("*")
@Api(value = "Stores")
public class StoreResource {

    private final StoreService storeService;

    @Autowired
    public StoreResource(StoreService storeService) {
        this.storeService = storeService;
    }

    @ApiOperation(
            value = "List all Store",
            response = String.class,
            notes = "This operation creates a new Store.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Success",
                response = String.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Error to return Stores",
                response = String.class
        )

    })
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(this.storeService.findAllStore(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Stores By Id")
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.storeService.findStoreById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create New Store")
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@Valid @RequestBody Store store) throws Exception {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(storeService.createStore(store), HttpStatus.CREATED);

        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw e;
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update Store")
    @RequestMapping(method = RequestMethod.PUT, value = "{storeId}")
    public ResponseEntity<?> update(@Valid @RequestBody Store store, @PathVariable("storeId") Long storeId) throws Exception {
        ResponseEntity<?> responseEntity = null;
        try {
            storeService.updateStore(store, storeId);
            responseEntity = new ResponseEntity<>(store, HttpStatus.OK);

        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw e;
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete Store")
    @RequestMapping(method = RequestMethod.DELETE, value = "{storeId}")
    public void delete(@PathVariable("storeId") Long storeId) throws Exception {
        storeService.deleteStore(storeId);
    }
}
