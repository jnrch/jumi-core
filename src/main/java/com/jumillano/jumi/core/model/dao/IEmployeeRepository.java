package com.jumillano.jumi.core.model.dao;

import com.jumillano.jumi.core.model.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IEmployeeRepository extends MongoRepository<Employee, Long> {
}
