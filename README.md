# Employee Management System

This is a simple, beginner-friendly **Spring Boot** application designed to manage employee data. It features full CRUD (Create, Read, Update, Delete) operations, role-based access control, search functionality, and basic statistical reporting.

## 🎯 Features

* **Employee CRUD Operations**: Add, update, and delete employee records.
* **Role-Based Access Control**: Different access levels for `ADMIN`, `MANAGER`, and `EMPLOYEE` roles.
* **Search and Filter**: Easily find employees by department or job title.
* **Reporting**: Generate real-time statistics on employee data.
* **Logging**: Actions are logged for auditing and error handling.
* **Testing**: Includes unit and integration tests using JUnit.

## 🚀 Technologies Used

* **Backend**: Java, Spring Boot
* **Database**: MySQL
* **API Testing**: Postman
* **IDE**: Eclipse

## 🔑 Role-Based Access Control

The application uses **Spring Security** to manage user permissions. Different roles have different access levels, which can be verified using the following credentials:

* **ADMIN**: `username: admin`, `password: adminpass` (Full access)
* **MANAGER**: `username: manager`, `password: managerpass` (Read-only access to employee list)
* **EMPLOYEE**: `username: employee`, `password: employeepass` (Read-only access to single employee record)

A **successful** request with a Manager's credentials shows their read-access is working.
