package com.acme.security.application.controller;

import com.acme.security.infra.security.CredentialsService;
import com.acme.security.infra.security.JWTUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jose.diegues
 */
@RestController
@RequestMapping(value = "security")
public class LoginController {

    private JWTUtil jwtUtil;

    @Autowired
    public LoginController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        String token = jwtUtil.generateToken(CredentialsService.authenticated());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

}
