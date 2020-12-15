package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IProviderRepository extends MongoRepository<Provider, Long> {
    Optional<Provider> findById(String id);

    void deleteById(String id);
}
