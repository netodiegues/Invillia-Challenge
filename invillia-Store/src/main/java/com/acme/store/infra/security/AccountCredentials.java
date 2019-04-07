package com.acme.store.infra.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author jose.diegues
 */
public class AccountCredentials implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public AccountCredentials() {

    }

    public AccountCredentials(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public AccountCredentials(Long id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public AccountCredentials(Long id, String username, String password, Set<Roles> perfis) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Roles roles) {
        return getAuthorities().contains(new SimpleGrantedAuthority(roles.getDescricao()));
    }
}
