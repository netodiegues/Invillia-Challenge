package com.acme.security.infra.persistence.repository;

import com.acme.security.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.diegues
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

}
