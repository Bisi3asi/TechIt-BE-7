package com.example.jpa_practice.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.jpa_practice.domain.entity.Company;
import com.example.jpa_practice.domain.entity.CompanyDto;
import com.example.jpa_practice.domain.entity.Employee;
import com.example.jpa_practice.domain.entity.EmployeeDto;
import com.example.jpa_practice.domain.repository.CompanyRepository;
import com.example.jpa_practice.domain.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {
	private final CompanyRepository companyRepository;
	private final EmployeeRepository employeeRepository;

	public CompanyDto get() {
		Optional<Company> opCompany = companyRepository.findById(1L);
		Company company = opCompany.get();

		List<EmployeeDto> employeeDtos = company.getEmployees().stream()
			.map(employee -> EmployeeDto.builder()
				.employeeId(employee.getEmployeeId())
				.employeeName(employee.getName())
				.companyId(employee.getCompany().getCompanyId())
				.build())
			.toList();

		return CompanyDto.builder()
			.companyId(company.getCompanyId())
			.companyName(company.getName())
			.employees(employeeDtos)
			.build();
	}

	// ABC company 의 맨 첫번째 ID의 사원 이름을 수정하는 메소드
	public void updateEmployeeByCompany() {
		Optional<Company> opCompany = companyRepository.findById(1L);
		Company company = opCompany.get();

		// 영속성 컨텍스트에 의해 관리되는 상태의 엔티티가 아닌 새로운 엔티티 생성
		Employee employee = new Employee();
		employee.setName("Foo");
		employeeRepository.save(employee);

		company.getEmployees().set(company.getEmployees().size() - 1, employee);
		companyRepository.save(company);
	}
}
