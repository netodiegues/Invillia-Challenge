package com.acme.store.infra.persistence.repository;

import com.acme.store.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.diegues
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
