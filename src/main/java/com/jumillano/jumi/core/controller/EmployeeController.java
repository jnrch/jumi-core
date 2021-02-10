package com.jumillano.jumi.core.controller;

import com.jumillano.jumi.core.model.entity.Employee;
import com.jumillano.jumi.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/employees")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('LEADER') or hasRole('ADMIN')")
    public List<Employee> findAll(@RequestParam(required = false) String name) {
        return employeeService.findAll(name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('LEADER') or hasRole('ADMIN')")
    public Optional<Employee> findById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('LEADER') or hasRole('ADMIN')")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LEADER') or hasRole('ADMIN')")
    public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }


}
