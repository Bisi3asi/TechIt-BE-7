package com.example.practice_springbatch.batch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {
	private final BatchService batchService;

	@GetMapping("/hello")
	public String runHelloJob() {
		batchService.runHelloJob();

		return "helloJob OK";
	}
}
