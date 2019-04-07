package com.acme.wrapper.application.resource.security;

import com.acme.wrapper.application.exception.ValidationErrorException;
import com.acme.wrapper.domain.model.dto.UserDto;
import com.acme.wrapper.domain.service.security.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

/**
 *
 * @author jose.diegues
 */
@RestController
@RequestMapping("wrapper/authentication")
@RequestScope
@Api(value = "Login")
public class LoginResource {

    private final AuthenticationService authenticationService;

    @Autowired
    public LoginResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody UserDto userDto) throws Exception {
        try {
            return authenticationService.login(userDto);
        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("refresh")
    public ResponseEntity<?> refresh() throws Exception {
        try {
            return authenticationService.refresh();
        } catch (ValidationErrorException ve) {
            return new ResponseEntity<>(ve.getErrors(), HttpStatus.BAD_REQUEST);

        }
    }
}
