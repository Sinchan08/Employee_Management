package com.demo.employeemanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
    private String jobTitle;
    private double salary;
    private LocalDate hireDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public Employee() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

   

    public LocalDate getHireDate() {
		return hireDate;
	}


	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}


	public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	public Employee(Long id, String name, String department, String jobTitle, double salary, LocalDate hireDate,
			Role role) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.hireDate = hireDate;
		this.role = role;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", jobTitle=" + jobTitle
				+ ", salary=" + salary + ", hireDate=" + hireDate + ", role=" + role + "]";
	}
    
}