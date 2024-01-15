package com.example.jpa_practice.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa_practice.domain.entity.Company;
import com.example.jpa_practice.domain.entity.Employee;
import com.example.jpa_practice.domain.entity.EmployeeDto;
import com.example.jpa_practice.domain.repository.CompanyRepository;
import com.example.jpa_practice.domain.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final CompanyRepository companyRepository;

	// 조회
	public List<EmployeeDto> get() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream()
			.map(employee -> EmployeeDto.builder()
				.employeeId(employee.getEmployeeId())
				.employeeName(employee.getName())
				.companyId(employee.getCompany().getCompanyId())
				.build())
			.toList();
	}

	// employee를 통해 company의 이름을 수정하는 메소드
	public void updateCompanyByEmployee() {
		Optional<Employee> opEmployee = employeeRepository.findById(1L);
		Employee employee = opEmployee.get();

		// 영속성 컨텍스트에 의해 관리되는 상태의 엔티티가 아닌 새로운 엔티티 생성
		Company company = new Company();
		company.setName("New Company");
		companyRepository.save(company);

		employee.setCompany(company);
		employeeRepository.save(employee);
		// employee의 companyId(FK)는 null로 연관관계 매핑 X, 이후 컨트롤러에서 NPE 발생
	}
}
