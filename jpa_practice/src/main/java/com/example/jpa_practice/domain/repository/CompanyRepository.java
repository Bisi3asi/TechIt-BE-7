package com.example.jpa_practice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_practice.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
