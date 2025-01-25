package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Employee;

public interface EmployeeService {
	Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employeeDetails);
    void deleteEmployee(Long id);

}
