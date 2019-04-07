package com.acme.store.domain.service;

import com.acme.store.domain.model.Store;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.acme.store.infra.persistence.repository.StoreRepository;

/**
 *
 * @author jose.diegues
 */
@Service
public class StoreService implements ServiceInterface<Store, Long> {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository entityRepository) {
        this.storeRepository = entityRepository;
    }

    public Page<Store> findAllPagination(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(Long id) {
        return storeRepository.getOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Store create(Store model) {
        return storeRepository.save(model);
    }

    @Override
    public Store update(Store model) {
        return storeRepository.save(model);
    }

    @Override
    public boolean deleteById(Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
