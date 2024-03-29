package com.example.practicesbb.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLogRepository extends JpaRepository<ProductLog, Long>{
	boolean existsByProduct(Product product);
}
