package com.example.practicesbb.global;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.example.practicesbb.domain.product.ProductService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InitData {
	@Autowired
	@Lazy
	private InitData self;
	private final ProductService productService;

	@Bean
	ApplicationRunner applicationRunner() {
		return args -> self.work1();
	}

	@Transactional
	public void work1() {
		IntStream.rangeClosed(1, 100).forEach(i ->
			productService.create("product " + i));
	}
}
