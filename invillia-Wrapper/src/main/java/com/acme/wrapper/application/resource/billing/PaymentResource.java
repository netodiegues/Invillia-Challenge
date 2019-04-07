package com.acme.wrapper.application.resource.billing;

import com.acme.wrapper.application.exception.ValidationErrorException;
import com.acme.wrapper.domain.model.billing.Payment;
import com.acme.wrapper.domain.service.billing.PaymentService;
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
@RequestMapping(value = "wrapper/payments")
@CrossOrigin("*")
@Api(value = "Payments")
public class PaymentResource {

    private final PaymentService paymentService;

    @Autowired
    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ApiOperation(
            value = "List all Payment",
            response = String.class,
            notes = "This operation creates a new Payment.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Success",
                response = String.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Error to return Payments",
                response = String.class
        )

    })
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(this.paymentService.findAllPayment(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Payments By Id")
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.paymentService.findPaymentById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create New Payment")
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@Valid @RequestBody Payment payment, @PathVariable Long storeId) throws Exception {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(paymentService.createPayment(payment, storeId), HttpStatus.CREATED);

        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw e;
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update Payment")
    @RequestMapping(method = RequestMethod.PUT, value = "{paymentId}")
    public ResponseEntity<?> update(@Valid @RequestBody Payment payment, @PathVariable("paymentId") Long paymentId) throws Exception {
        ResponseEntity<?> responseEntity = null;
        try {
            paymentService.updatePayment(payment, paymentId);
            responseEntity = new ResponseEntity<>(payment, HttpStatus.OK);

        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw e;
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete Payment")
    @RequestMapping(method = RequestMethod.DELETE, value = "{paymentId}")
    public void delete(@PathVariable("paymentId") Long paymentId) throws Exception {
        paymentService.deletePayment(paymentId);
    }
}
