package com.example.opencsv.Service;



import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.opencsv.entity.Employee;
import com.example.opencsv.repository.EmployeeRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void loadCSVData() {
        try {
            // Load the CSV file from the resources folder
            ClassPathResource resource = new ClassPathResource("employees.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            // Parse the CSV file using Apache Commons CSV
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            // Loop through each record and save it to the database
            for (CSVRecord record : parser) {
                String name = record.get("name");
                int age = Integer.parseInt(record.get("age"));

                // Create an Employee object and save it to the database
                Employee employee = new Employee();
                employee.setName(name);
                employee.setAge(age);
                employeeRepository.save(employee);
            }

            parser.close();
        } catch (Exception e) {
            throw new RuntimeException("Error while loading CSV data: " + e.getMessage(), e);
        }
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
