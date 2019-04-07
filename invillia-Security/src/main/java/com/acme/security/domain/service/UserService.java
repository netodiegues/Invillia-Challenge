package com.acme.security.domain.service;

import com.acme.security.domain.model.User;
import com.acme.security.infra.persistence.repository.UserRepository;
import com.acme.security.infra.security.AccountCredentials;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author jose.diegues
 */
@Service
public class UserService implements ServiceInterface<User, Long>, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository entityRepository) {
        this.userRepository = entityRepository;
    }

    public Page<User> findAllPagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        AccountCredentials accountCredentials = null;

        if (user != null) {
            accountCredentials = new AccountCredentials(user.getId(), user.getUsername(), user.getPassword());
        } else {
            accountCredentials = new AccountCredentials();
        }

        return accountCredentials;
    }

}
