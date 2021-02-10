package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IEmployeeDao extends MongoRepository<Employee, String> {

    Optional<Employee> findById(String id);
    void deleteById(String id);
    List<Employee> findByNameContaining(String name);
}
