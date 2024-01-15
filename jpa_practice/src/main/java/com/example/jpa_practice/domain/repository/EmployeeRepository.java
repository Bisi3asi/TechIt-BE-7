package com.example.jpa_practice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_practice.domain.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
