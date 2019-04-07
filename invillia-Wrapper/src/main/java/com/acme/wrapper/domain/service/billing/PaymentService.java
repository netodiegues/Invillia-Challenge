package com.acme.wrapper.domain.service.billing;

import com.acme.wrapper.application.exception.ValidationErrorException;
import com.acme.wrapper.domain.model.billing.Payment;
import com.acme.wrapper.domain.model.restTemplate.RestResponsePage;
import com.acme.wrapper.domain.service.store.StoreService;
import com.acme.wrapper.infra.persistence.handler.RestClientExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.List;
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
public class PaymentService {

    @Value("${acme.api-url.billing}")
    private String URL;

    private final RestTemplate restTemplate;
    private final StoreService storeService;

    public PaymentService(RestTemplateBuilder restTemplateBuilder, StoreService storeService) {
        this.restTemplate = restTemplateBuilder.build();
        this.storeService = storeService;
    }

    public List<Payment> findAllPayment(Pageable pageable) {

        ParameterizedTypeReference<RestResponsePage<Payment>> responseType
                = new ParameterizedTypeReference<RestResponsePage<Payment>>() {
        };

        URI targetUrl = UriComponentsBuilder.fromUriString(URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", pageable.getSort().isSorted() ? pageable.getSort() : null)
                .build()
                .toUri();

        ResponseEntity<RestResponsePage<Payment>> result = this.restTemplate.exchange(targetUrl.toString().replaceAll(":%20", ","), HttpMethod.GET, null, responseType);
        List<Payment> searchResult = result.getBody().getContent();
        return searchResult;

    }

    public Payment findPaymentById(Long paymentId) {
        Payment payment = this.restTemplate.getForObject(URL + "/{paymentId}", Payment.class, paymentId);
        return payment;
    }

    public Payment createPayment(Payment payment, Long storeId) throws Exception {

        Payment response = null;
        try {
            payment.setStoreId(storeService.findStoreById(storeId).getId());
            response = this.restTemplate.postForObject(URL, payment, Payment.class);

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

    public void updatePayment(Payment payment, Long paymentId) throws Exception {
        try {
            restTemplate.put(URL + "/{paymentId}", payment, paymentId);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "update");
        }
    }

    public void deletePayment(Long paymentId) throws Exception {
        try {
            restTemplate.delete(URL + "/{id}", paymentId);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "delete");
        }
    }

}
