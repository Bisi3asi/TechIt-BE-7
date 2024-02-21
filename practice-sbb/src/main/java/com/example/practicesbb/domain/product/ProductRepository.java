package com.example.practicesbb.domain.product;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

}
