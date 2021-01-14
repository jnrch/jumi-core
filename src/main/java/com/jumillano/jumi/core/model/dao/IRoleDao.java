package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IRoleDao extends MongoRepository<Role, String> {
    Optional<Role> findByName(com.jumillano.jumi.core.model.enums.Role name);
}
