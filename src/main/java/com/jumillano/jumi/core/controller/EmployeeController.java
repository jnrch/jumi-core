package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.Employee;
import com.jumillano.jumi.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    private List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    private Optional<Employee> findById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @PostMapping
    private Employee saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/{id}")
    private Employee updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    private void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }
}
