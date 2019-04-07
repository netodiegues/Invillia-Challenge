package com.acme.wrapper.domain.service.security;

import com.acme.wrapper.domain.model.dto.UserDto;
import com.acme.wrapper.infra.persistence.handler.RestClientExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jose.diegues
 */
@Service
public class AuthenticationService {

    @Value("${acme.api-url.security}")
    private String URL;

    private final RestTemplate restTemplate;

    @Autowired
    public AuthenticationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<?> refresh() throws Exception {
        ResponseEntity<?> response = null;
        try {
            response = this.restTemplate.postForEntity(URL + "/security/refresh-token", null, String.class);
        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "refresh");
        }
        return response;
    }

    public HttpEntity<?> login(UserDto userDto) throws Exception {
        HttpEntity<?> entity = new HttpEntity<>(userDto);
        HttpEntity<String> response = null;

        try {
            response = restTemplate.exchange(URL + "/login", HttpMethod.POST, entity, String.class);

        } catch (HttpStatusCodeException e) {
            RestClientExceptionHandler restClientExceptionHandler = new RestClientExceptionHandler(e, URL, "login");
        }
        return response;
    }
}
