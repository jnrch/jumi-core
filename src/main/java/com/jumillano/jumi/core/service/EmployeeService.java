package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IEmployeeDao;
import com.jumillano.jumi.core.model.entity.Employee;
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
    private IEmployeeDao employeeDao;

    @Autowired
    public EmployeeService(IEmployeeDao iEmployeeDao) {
        this.employeeDao = iEmployeeDao;
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    public Optional<Employee> findById(String id) {
        return employeeDao.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    public Employee updateEmployee(String id, Employee employee) {

        Optional<Employee> currentEmployee = findById(id);

        employee.setId(new ObjectId(String.valueOf(currentEmployee.get().getId())));
        employeeDao.save(employee);

        return employee;
    }

    public void deleteEmployee(String id) {
        employeeDao.deleteById(id);
    }
}
