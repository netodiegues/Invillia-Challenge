package com.acme.wrapper.domain.service.store;

import com.acme.wrapper.domain.model.store.Store;
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
public class StoreService {

    @Value("${acme.api-url.store}")
    private String URL;

    private final RestTemplate restTemplate;

    public StoreService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Store> findAllStore(Pageable pageable) {

        ParameterizedTypeReference<RestResponsePage<Store>> responseType
                = new ParameterizedTypeReference<RestResponsePage<Store>>() {
        };

        URI targetUrl = UriComponentsBuilder.fromUriString(URL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", pageable.getSort().isSorted() ? pageable.getSort() : null)
                .build()
                .toUri();

        ResponseEntity<RestResponsePage<Store>> result = this.restTemplate.exchange(targetUrl.toString().replaceAll(":%20", ","), HttpMethod.GET, null, responseType);
        List<Store> searchResult = result.getBody().getContent();
        return searchResult;

    }

    public Store findStoreById(Long storeId) {
        Store store = this.restTemplate.getForObject(URL + "/{storeId}", Store.class, storeId);
        return store;
    }

    public Store createStore(Store store) throws Exception {
        return this.restTemplate.postForObject(URL, store, Store.class);
    }

    public void updateStore(Store store, Long storeId) throws Exception {
        try {
            restTemplate.put(URL + "/{storeId}", store, storeId);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "update");
        }
    }

    public void deleteStore(Long storeId) throws Exception {
        try {
            restTemplate.delete(URL + "/{id}", storeId);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "delete");
        }
    }

}
