package com.example.practicesbb.batch;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.Job;

@Configuration
public class BatchTestConfig {

	@Bean
	public JobLauncherTestUtils helloJobLauncherTestUtils(Job helloJob) {
		JobLauncherTestUtils utils = new JobLauncherTestUtils();
		utils.setJob(helloJob);
		return utils;
	}

	@Bean
	public JobLauncherTestUtils hello2JobLauncherTestUtils(Job hello2Job) {
		JobLauncherTestUtils utils = new JobLauncherTestUtils();
		utils.setJob(hello2Job);
		return utils;
	}

	@Bean
	public JobLauncherTestUtils hello3JobLauncherTestUtils(Job hello3Job) {
		JobLauncherTestUtils utils = new JobLauncherTestUtils();
		utils.setJob(hello3Job);
		return utils;
	}

	@Bean
	public JobLauncherTestUtils hello4JobLauncherTestUtils(Job hello4Job) {
		JobLauncherTestUtils utils = new JobLauncherTestUtils();
		utils.setJob(hello4Job);
		return utils;
	}

	@Bean
	public JobLauncherTestUtils hello5JobLauncherTestUtils(Job hello5Job) {
		JobLauncherTestUtils utils = new JobLauncherTestUtils();
		utils.setJob(hello5Job);
		return utils;
	}

	@Bean
	public JobLauncherTestUtils makeProductLogJobLauncherTestUtils(Job makeProductLogJob) {
		JobLauncherTestUtils utils = new JobLauncherTestUtils();
		utils.setJob(makeProductLogJob);
		return utils;
	}
}
