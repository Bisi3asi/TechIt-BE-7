package com.example.practicesbb.batch;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatchService {
	private final JobLauncher jobLauncher;
	private final Job hello2Job;
	private final Job makeProductLogJob;

	public void runHelloJob() {
		try {
			JobParameters jobParameters = new JobParametersBuilder()
				.addLong("id", System.currentTimeMillis())
				.toJobParameters();
			jobLauncher.run(hello2Job, jobParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runMakeProductLogJob() {
		try {
			String startDate = LocalDateTime.now().minusDays(1).toString().substring(0, 10) + " 00:00:00.000000";
			String endDate = LocalDateTime.now().minusDays(1).toString().substring(0, 10) + " 23:59:59.999999";

			JobParameters jobParameters = new JobParametersBuilder()
				.addString("startDate", startDate)
				.addString("endDate", endDate)
				.toJobParameters();
			jobLauncher.run(makeProductLogJob, jobParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}