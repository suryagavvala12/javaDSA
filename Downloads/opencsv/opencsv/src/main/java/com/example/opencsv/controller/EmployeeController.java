package com.example.opencsv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.opencsv.Service.EmployeeService;
import com.example.opencsv.entity.Employee;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/load-csv")
    public String loadCSV() {
        try {
            employeeService.loadCSVData();
            return "CSV Data Loaded Successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }
}

