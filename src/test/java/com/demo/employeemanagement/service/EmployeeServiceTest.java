package com.demo.employeemanagement.service;

import com.demo.employeemanagement.entities.Employee;
import com.demo.employeemanagement.repository.EmployeeRepository;
import com.demo.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void whenGetEmployeeById_thenEmployeeIsReturned() {
        Employee employee = new Employee();
        employee.setId(1L);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> foundEmployee = employeeService.getEmployeeById(1L);
        assertTrue(foundEmployee.isPresent());
        assertEquals(1L, foundEmployee.get().getId());
    }

    @Test
    public void whenDeleteEmployee_thenEmployeeIsRemoved() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        boolean isDeleted = employeeService.deleteEmployee(1L);
        assertTrue(isDeleted);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
