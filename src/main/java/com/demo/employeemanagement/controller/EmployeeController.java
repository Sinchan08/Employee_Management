package com.demo.employeemanagement.controller;

import com.demo.employeemanagement.entities.Employee;
import com.demo.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// This class handles all the web requests for our employee management system.
// It acts as a bridge between the user's request and our service logic.
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // Spring will automatically create and connect an instance of EmployeeService for us.
    @Autowired
    private EmployeeService employeeService;

    // This method gets a list of all employees.
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // This method gets a single employee by their ID.
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        
        // We check if the employee was found.
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get()); // Return the employee with a 200 OK status.
        } else {
            return ResponseEntity.notFound().build(); // Return a 404 Not Found status.
        }
    }

    // This method creates a new employee from the data sent by the user.
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // This method updates an existing employee's details.
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);

        // We check if the update was successful.
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // This method deletes an employee from the system.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        
        // We check if the deletion was successful.
        if (isDeleted) {
            return ResponseEntity.ok().build(); // Return a 200 OK status.
        } else {
            return ResponseEntity.notFound().build(); // Return a 404 Not Found status.
        }
    }

    // This method finds employees by their department.
    @GetMapping("/search/department")
    public List<Employee> findByDepartment(@RequestParam String department) {
        return employeeService.findEmployeesByDepartment(department);
    }

    // This method finds employees by their job title.
    @GetMapping("/search/jobTitle")
    public List<Employee> findByJobTitle(@RequestParam String jobTitle) {
        return employeeService.findEmployeesByJobTitle(jobTitle);
    }
}