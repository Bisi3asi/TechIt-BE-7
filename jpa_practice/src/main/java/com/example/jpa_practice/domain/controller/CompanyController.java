package com.example.jpa_practice.domain.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_practice.domain.entity.CompanyDto;
import com.example.jpa_practice.domain.entity.EmployeeDto;
import com.example.jpa_practice.domain.service.CompanyService;
import com.example.jpa_practice.domain.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CompanyController {
	private final CompanyService companyService;
	private final EmployeeService employeeService;

	@GetMapping
	public CompanyDto showAll() {
		return companyService.get();
	}

	@GetMapping("/employees")
	public List<EmployeeDto> showEmployees() {
		return employeeService.get();
	}

	@GetMapping("/updatebycompany")
	public String updateEmployeeByCompany() {
		companyService.updateEmployeeByCompany();
		return "update complete";
	}

	@GetMapping("/updatebyemployee")
	public String updateEmployee() {
		employeeService.updateCompanyByEmployee();
		return "update complete";
	}
}

