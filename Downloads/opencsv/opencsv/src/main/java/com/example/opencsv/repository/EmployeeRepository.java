package com.example.opencsv.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.opencsv.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
