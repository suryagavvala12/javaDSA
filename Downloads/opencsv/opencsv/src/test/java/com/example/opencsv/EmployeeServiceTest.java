package com.example.opencsv;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import com.example.opencsv.Service.EmployeeService;
import com.example.opencsv.entity.Employee;
import com.example.opencsv.repository.EmployeeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testLoadCSVData() throws Exception {
        // Mocking the save method of the repository
        doNothing().when(employeeRepository).save(any(Employee.class));

        // Calling the service method
        employeeService.loadCSVData();

        // Verifying that the save method is called the expected number of times
        verify(employeeRepository, times(3)).save(any(Employee.class));
    }

    @Test
    void testGetAllEmployees() {
        // Mocking the repository to return a predefined list of employees
        List<Employee> mockEmployees = List.of(
                new Employee() {{ setId(1L); setName("John Doe"); setAge(30); }},
                new Employee() {{ setId(2L); setName("Jane Smith"); setAge(25); }}
        );
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        // Calling the service method
        List<Employee> employees = employeeService.getAllEmployees();

        // Assertions
        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals(30, employees.get(0).getAge());
    }
}

