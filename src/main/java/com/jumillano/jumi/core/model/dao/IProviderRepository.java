package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProviderRepository extends MongoRepository<Provider, Long> {
}
