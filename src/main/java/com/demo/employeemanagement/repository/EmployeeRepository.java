package com.demo.employeemanagement.repository;

import com.demo.employeemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(String department);
    List<Employee> findByJobTitle(String jobTitle);
    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> getEmployeeCountByDepartment();

    // Custom query to get average salary
    @Query("SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    //for getting top 5 highest paid employees
    List<Employee> findTop5ByOrderBySalaryDesc();
}
