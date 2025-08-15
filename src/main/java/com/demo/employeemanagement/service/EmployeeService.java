package com.demo.employeemanagement.service;

import com.demo.employeemanagement.entities.Employee;
import com.demo.employeemanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees.");
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        logger.info("Fetching employee with id: {}", id);
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        logger.info("Creating a new employee: {}", employee.getName());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        logger.info("Updating employee with id: {}", id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDetails.getName());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setJobTitle(employeeDetails.getJobTitle());
            employee.setSalary(employeeDetails.getSalary());
            employee.setHireDate(employeeDetails.getHireDate());
            employee.setRole(employeeDetails.getRole());
            return employeeRepository.save(employee);
        } else {
            logger.error("Employee with id {} not found.", id);
            return null; // Or throw an exception
        }
    }

    public boolean deleteEmployee(Long id) {
        logger.info("Deleting employee with id: {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            logger.error("Employee with id {} not found for deletion.", id);
            return false;
        }
    }

    public List<Employee> findEmployeesByDepartment(String department) {
        logger.info("Searching for employees in department: {}", department);
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> findEmployeesByJobTitle(String jobTitle) {
        logger.info("Searching for employees with job title: {}", jobTitle);
        return employeeRepository.findByJobTitle(jobTitle);
    }
    public long countEmployees() {
        return employeeRepository.count();
    }

    public List<Object[]> getEmployeeCountByDepartment() {
        return employeeRepository.getEmployeeCountByDepartment();
    }

    public double getAverageSalary() {
        return employeeRepository.getAverageSalary();
    }

    public List<Employee> getTop5HighestPaidEmployees() {
        return employeeRepository.findTop5ByOrderBySalaryDesc();
    }
}
