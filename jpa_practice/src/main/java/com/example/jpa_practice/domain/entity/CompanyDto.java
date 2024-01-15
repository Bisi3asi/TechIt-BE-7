package com.example.jpa_practice.domain.entity;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CompanyDto {
	private Long companyId;
	private String companyName;
	private List<EmployeeDto> employees;
}
