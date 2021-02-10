package com.jumillano.jumi.core.service;

import com.jumillano.jumi.core.model.dao.IEmployeeDao;
import com.jumillano.jumi.core.model.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private IEmployeeDao employeeDao;

    @Autowired
    public EmployeeService(IEmployeeDao empleadoDao) {
        this.employeeDao = empleadoDao;
    }

    public List<Employee> findAll(String name) {
        List<Employee> employees;

        if (name == null) {
            employees = employeeDao.findAll();
        } else {
            employees = employeeDao.findByNameContaining(name);
        }
        return employees;
    }

    public Optional<Employee> findById(String id) {
        return employeeDao.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    public Employee updateEmployee(String id, Employee employee) {
        Optional<Employee> currentEmployee = findById(id);

        employee.setId(currentEmployee.get().getId());
        employeeDao.save(employee);

        return employee;
    }

    public void deleteEmployee(String id) {
        employeeDao.deleteById(id);
    }

}
