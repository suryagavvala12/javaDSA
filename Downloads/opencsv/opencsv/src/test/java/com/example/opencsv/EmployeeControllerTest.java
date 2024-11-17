package com.example.opencsv;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.opencsv.Service.EmployeeService;
import com.example.opencsv.controller.EmployeeController;
import com.example.opencsv.entity.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testLoadCSV() {
        // Mock the service call to load CSV data
        doNothing().when(employeeService).loadCSVData();

        // Call the controller method
        String response = employeeController.loadCSV();

        // Assert the response
        assertEquals("CSV data loaded successfully!", response);

        // Verify that the service method was called
        verify(employeeService, times(1)).loadCSVData();
    }

    @Test
    void testGetEmployees() {
        // Mocking the service to return a predefined list of employees
        List<Employee> mockEmployees = List.of(
                new Employee() {{ setId(1L); setName("John Doe"); setAge(30); }},
                new Employee() {{ setId(2L); setName("Jane Smith"); setAge(25); }}
        );
        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);

        // Call the controller method
        List<Employee> employees = employeeController.getEmployees();

        // Assertions
        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals(30, employees.get(0).getAge());
    }
}
