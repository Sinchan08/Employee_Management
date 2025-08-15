package com.demo.employeemanagement.controller;

import com.demo.employeemanagement.entities.Employee;
import com.demo.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/statistics")
    public Map<String, Object> getEmployeeStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalEmployees", employeeService.countEmployees());
        statistics.put("averageSalary", employeeService.getAverageSalary());
        statistics.put("employeesByDepartment", employeeService.getEmployeeCountByDepartment());
        return statistics;
    }

    @GetMapping("/top-paid")
    public List<Employee> getTop5HighestPaidEmployees() {
        return employeeService.getTop5HighestPaidEmployees();
    }
}
