package com.acme.security.domain.service;

import java.util.List;

/**
 *
 * @author jose.diegues
 * @param <T>
 * @param <ID>
 */
public interface ServiceInterface<T, ID> {

    public List<T> findAll();

    public T findById(Long id);

    public boolean existsById(Long id);

}
