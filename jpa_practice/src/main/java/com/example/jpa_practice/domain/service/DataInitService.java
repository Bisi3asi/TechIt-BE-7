package com.example.jpa_practice.domain.service;

import org.springframework.stereotype.Service;

import com.example.jpa_practice.domain.entity.Company;
import com.example.jpa_practice.domain.entity.Employee;
import com.example.jpa_practice.domain.repository.CompanyRepository;
import com.example.jpa_practice.domain.repository.EmployeeRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataInitService {
	private final CompanyRepository companyRepository;
	private final EmployeeRepository employeeRepository;

	@PostConstruct
	public void init() {
		// 회사 생성
		Company company = new Company();
		company.setName("ABC Company");
		companyRepository.save(company);

		// 직원 생성
		Employee employee1 = new Employee();
		employee1.setName("John Doe");
		employee1.setCompany(company);
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setName("Jane Doe");
		employee2.setCompany(company);
		employeeRepository.save(employee2);
		}
	}
