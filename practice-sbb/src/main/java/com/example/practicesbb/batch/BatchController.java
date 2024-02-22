package com.example.practicesbb.batch;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {
	private final BatchService batchService;

	@GetMapping("/hello")
	@ResponseBody
	public String runHelloJob() {
		batchService.runHelloJob();

		return "runSimpleJob OK";
	}

	@GetMapping("/job")
	@ResponseBody
	public String runMakeProductLogJob() {
		LocalDateTime startDate = LocalDateTime
			.now()
			.withHour(0)
			.withMinute(0)
			.withSecond(0)
			.withNano(0);

		LocalDateTime endDate = LocalDateTime
			.now()
			.withHour(23)
			.withMinute(59)
			.withSecond(59)
			.withNano(999999);

		batchService.runMakeProductLogJob(
			startDate,
			endDate
		);

		return "runMakeProductLogJob OK";
	}
}