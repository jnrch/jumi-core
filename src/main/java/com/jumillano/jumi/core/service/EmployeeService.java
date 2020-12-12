package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IEmployeeRepository;
import com.jumillano.jumi.core.model.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private IEmployeeRepository iEmployeeRepository;

    @Autowired
    public EmployeeService(IEmployeeRepository iEmployeeRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return iEmployeeRepository.save(employee);
    }
}
