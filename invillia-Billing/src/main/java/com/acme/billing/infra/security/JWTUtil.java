package com.acme.billing.infra.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author jose.diegues
 */
@Component
public class JWTUtil {

    @Value("${acme.jwt.secret}")
    private String secret;

    @Value("${acme.jwt.expiration}")
    private Long expiration;

    public String generateToken(AccountCredentials accountCredentials) {

        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("username", accountCredentials.getUsername());
        claims.put("id", accountCredentials.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public boolean tokenValidator(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String user = claims.get("username", String.class);

            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (user != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getUser(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.get("username", String.class);
        }
        return null;
    }

    public Long getId(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.get("id", Long.class);
        }
        return null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

}
