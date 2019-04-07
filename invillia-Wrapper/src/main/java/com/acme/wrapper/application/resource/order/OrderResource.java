package com.acme.wrapper.application.resource.order;

import com.acme.wrapper.application.exception.ValidationErrorException;
import com.acme.wrapper.domain.model.order.Order;
import com.acme.wrapper.domain.service.order.OrderService;
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
@RequestMapping(value = "wrapper/orders")
@CrossOrigin("*")
@Api(value = "Orders")
public class OrderResource {

    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(
            value = "List all Orders",
            response = String.class,
            notes = "This operation creates a new Order.")
    @ApiResponses(value = {
        @ApiResponse(
                code = 200,
                message = "Success",
                response = String.class
        )
        ,
			@ApiResponse(
                code = 500,
                message = "Error to return Orders",
                response = String.class
        )

    })
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(this.orderService.findAllOrder(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Order By Id")
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.orderService.findOrderById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create New Order")
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> create(@Valid @RequestBody Order order) throws Exception {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);

        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw e;
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update Order")
    @RequestMapping(method = RequestMethod.PUT, value = "{orderId}")
    public ResponseEntity<?> update(@Valid @RequestBody Order order, @PathVariable("orderId") Long orderId) throws Exception {
        ResponseEntity<?> responseEntity = null;
        try {
            orderService.updateOrder(order, orderId);
            responseEntity = new ResponseEntity<>(order, HttpStatus.OK);

        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw e;
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete Order")
    @RequestMapping(method = RequestMethod.DELETE, value = "{orderId}")
    public void delete(@PathVariable("orderId") Long orderId) throws Exception {
        orderService.deleteOrder(orderId);
    }
}
