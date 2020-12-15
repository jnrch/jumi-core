package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILocationDao extends MongoRepository<Location, String> {
}
