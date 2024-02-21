package com.example.practicesbb.domain.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	@Transactional
	public void create(String name) {
		Product product =
			Product.builder()
				.name(name)
				.build();

		productRepository.save(product);
	}
}
