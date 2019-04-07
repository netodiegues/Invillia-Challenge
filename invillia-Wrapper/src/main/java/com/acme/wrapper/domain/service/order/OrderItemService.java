package com.acme.wrapper.domain.service.order;

import com.acme.wrapper.application.exception.ValidationErrorException;
import com.acme.wrapper.domain.model.order.OrderItem;
import com.acme.wrapper.domain.model.restTemplate.RestResponsePage;
import com.acme.wrapper.infra.persistence.handler.RestClientExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.support.RepositoryConstraintViolationExceptionMessage.ValidationError;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author jose.diegues
 */
@Service
public class OrderItemService {

    @Value("${acme.api-url.order}")
    private String URL;

    private final RestTemplate restTemplate;
    private final OrderService orderService;

    public OrderItemService(RestTemplateBuilder restTemplateBuilder, OrderService orderService) {
        this.restTemplate = restTemplateBuilder.build();
        this.orderService = orderService;
    }

    public JSONObject findOrderItemById(Long orderId, Pageable pageable) throws Exception {
        JSONObject response = new JSONObject();
        try {
            ParameterizedTypeReference<RestResponsePage<OrderItem>> responseType
                    = new ParameterizedTypeReference<RestResponsePage<OrderItem>>() {
            };

            URI targetUrl = UriComponentsBuilder.fromUriString(URL)
                    .path("/{orderId}/items")
                    .queryParam("page", pageable.getPageNumber())
                    .queryParam("size", pageable.getPageSize())
                    .queryParam("sort", pageable.getSort().isSorted() ? pageable.getSort() : null)
                    .build()
                    .expand(orderId)
                    .encode()
                    .toUri();

            ResponseEntity<RestResponsePage<OrderItem>> result = this.restTemplate.exchange(targetUrl.toString().replaceAll(":%20", ","), HttpMethod.GET, null, responseType);
            List<OrderItem> orderItemResult = result.getBody().getContent();

            for (OrderItem orderItem : orderItemResult) {
                orderItem.setOrder(orderService.findOrderById(orderItem.getId()));
            }

            response.put("orderItem", orderItemResult);
            response.put("pageable", result.getBody().getContent());
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "findOrderItemById");
        }
        return response;
    }

    public OrderItem findOrderItemByIdOfOrder(Long orderId, Long orderItemId) throws Exception {
        OrderItem response = null;
        try {
            response = restTemplate.getForObject(URL + "/{orderId}/items/{orderItemId}", OrderItem.class, orderId, orderItemId);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "findOrderItemByIdOfOrder");
        }
        return response;
    }

    public OrderItem createOrderItem(Long orderId, OrderItem orderItem) throws Exception {
        OrderItem response = null;
        try {
            orderItem.setOrder(orderService.findOrderById(orderId));
            response = this.restTemplate.postForObject(URL + "/{orderId}/items", orderItem, OrderItem.class, orderId);

        } catch (HttpStatusCodeException e) {
            if (e.getResponseBodyAsString().contains("{\"field\":")) {
                ObjectMapper mapper = new ObjectMapper();
                ValidationError[] erros = mapper.readValue(e.getResponseBodyAsString(), ValidationError[].class);
                ValidationErrorException ex = new ValidationErrorException(e.getMessage());
                ex.setErrors(erros);
                throw ex;
            }

            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "create");
        }
        return response;
    }

    public void updateOrderItem(Long orderId, OrderItem orderItem) throws Exception {
        try {
            restTemplate.put(URL + "/{orderId}/items/{orderItem}", orderId, orderItem);

        } catch (HttpStatusCodeException e) {

            if (e.getResponseBodyAsString().contains("{\"field\":")) {
                ObjectMapper mapper = new ObjectMapper();
                ValidationError[] erros = mapper.readValue(e.getResponseBodyAsString(), ValidationError[].class);
                ValidationErrorException ex = new ValidationErrorException(e.getMessage());
                ex.setErrors(erros);
                throw ex;
            }
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "update");
        }
    }

    public void deleteOrderItem(Long orderId, Long orderItemId) throws Exception {
        try {
            restTemplate.delete(URL + "/{orderId}/items/{orderItemId}", orderId, orderItemId);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "deleteOrderItem");
        }
    }
}
