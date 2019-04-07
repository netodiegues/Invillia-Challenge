package com.acme.wrapper.application.resource.order;

import com.acme.wrapper.application.exception.ValidationErrorException;
import com.acme.wrapper.domain.model.order.OrderItem;
import com.acme.wrapper.domain.service.order.OrderItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "wrapper/orders/{orderId}/items")
@CrossOrigin("*")
@Api(value = "OrdersItems")
public class OrderItemResource {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemResource(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @ApiOperation(value = "Item by Id")
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<?> findOrderItemById(@PathVariable("orderId") Long orderId, Pageable pageable) throws Exception {
        return new ResponseEntity<>(this.orderItemService.findOrderItemById(orderId, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Item by Ordem")
    @RequestMapping(method = RequestMethod.GET, value = "{orderItemId}")
    public ResponseEntity<?> findOrderItemByIdOfOrder(@PathVariable("orderId") Long orderId, @PathVariable("orderItemId") Long orderItemId) throws Exception {
        return new ResponseEntity<>(this.orderItemService.findOrderItemByIdOfOrder(orderId, orderItemId), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new item")
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> createOrderItem(@Valid @RequestBody OrderItem orderItem, @PathVariable("orderId") Long orderId) throws Exception {
        ResponseEntity<OrderItem> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(orderItemService.createOrderItem(orderId, orderItem), HttpStatus.CREATED);

        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw e;
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update item")
    @RequestMapping(method = RequestMethod.PUT, value = "")
    public ResponseEntity<?> updateOrderItem(@Valid @RequestBody OrderItem orderItem, @PathVariable("orderId") Long orderId) throws Exception {

        ResponseEntity<OrderItem> responseEntity = null;

        try {
            orderItemService.updateOrderItem(orderId, orderItem);
            responseEntity = new ResponseEntity<>(orderItem, HttpStatus.OK);

        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw e;
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete Item")
    @RequestMapping(method = RequestMethod.DELETE, value = "{orderItemId}")
    public void deleteOrderItem(@PathVariable("orderId") Long orderId, @PathVariable("orderItemId") Long orderItemId) throws Exception {
        this.orderItemService.deleteOrderItem(orderId, orderItemId);
    }
}
