package com.acme.wrapper.infra.security;

import java.io.IOException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jose.diegues
 */
@Configuration
public class JWTAuthenticationInterceptor implements ClientHttpRequestInterceptor {

    private String getToken() {
        String token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
        if (token != null) {
            return token;
        }
        return "";
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add("Authorization", getToken());
        headers.add("access-control-expose-headers", "Authorization");
        return execution.execute(request, body);
    }

}
