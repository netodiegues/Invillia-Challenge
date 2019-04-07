package com.acme.store.infra.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jose.diegues
 */
public class CredentialsService {

    public static AccountCredentials authenticated() {
        try {
            return (AccountCredentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

}
