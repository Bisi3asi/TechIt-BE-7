package com.example.practicesbb.batch;

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

	@GetMapping("/simple")
	@ResponseBody
	public String runSimpleJob() {
		batchService.runSimpleJob();

		return "runSimpleJob OK";
	}
}