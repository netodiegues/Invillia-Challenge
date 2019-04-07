package com.acme.wrapper.domain.service.order;

import com.acme.wrapper.domain.model.order.Order;
import com.acme.wrapper.domain.model.restTemplate.RestResponsePage;
import com.acme.wrapper.infra.persistence.handler.RestClientExceptionHandler;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
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
public class OrderService {

    @Value("${acme.api-url.order}")
    private String URL;

    private final RestTemplate restTemplate;

    public OrderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Order> findAllOrder(Pageable pageable) {

        ParameterizedTypeReference<RestResponsePage<Order>> responseType
                = new ParameterizedTypeReference<RestResponsePage<Order>>() {
        };

        URI targetUrl = UriComponentsBuilder.fromUriString(URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", pageable.getSort().isSorted() ? pageable.getSort() : null)
                .build()
                .toUri();

        ResponseEntity<RestResponsePage<Order>> result = this.restTemplate.exchange(targetUrl.toString().replaceAll(":%20", ","), HttpMethod.GET, null, responseType);
        List<Order> searchResult = result.getBody().getContent();
        return searchResult;

    }

    public Order findOrderById(Long orderId) {
        Order order = this.restTemplate.getForObject(URL + "/{orderId}", Order.class, orderId);
        return order;
    }

    public Order createOrder(Order order) throws Exception {
        return this.restTemplate.postForObject(URL, order, Order.class);
    }

    public void updateOrder(Order order, Long orderId) throws Exception {
        try {
            restTemplate.put(URL + "/{orderId}", order, orderId);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "update");
        }
    }

    public void deleteOrder(Long orderId) throws Exception {
        try {
            restTemplate.delete(URL + "/{id}", orderId);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "delete");
        }
    }

}
