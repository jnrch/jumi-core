package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IEmployeeRepository extends MongoRepository<Employee, Long> {
    Optional<Employee> findById(String id);

    void deleteById(String id);
}
