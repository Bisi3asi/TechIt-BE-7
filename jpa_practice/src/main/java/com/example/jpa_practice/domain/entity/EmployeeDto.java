package com.example.jpa_practice.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmployeeDto {
	private Long employeeId;
	private String employeeName;
	private Long companyId;
}
