package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IEmployeeRepository;
import com.jumillano.jumi.core.model.entity.Employee;
import com.jumillano.jumi.core.model.entity.Provider;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private IEmployeeRepository iEmployeeRepository;

    @Autowired
    public EmployeeService(IEmployeeRepository iEmployeeRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
    }

    public List<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    public Optional<Employee> findById(String id) {
        return iEmployeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return iEmployeeRepository.save(employee);
    }

    public Employee updateEmployee(String id, Employee employee) {

        Optional<Employee> currentEmployee = findById(id);

        employee.setId(new ObjectId(String.valueOf(currentEmployee.get().getId())));
        iEmployeeRepository.save(employee);

        return employee;
    }

    public void deleteEmployee(String id) {
        iEmployeeRepository.deleteById(id);
    }
}
