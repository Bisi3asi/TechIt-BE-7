package com.example.practice_springbatch.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.core.Job;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class HelloJobConfig {
	@Bean
	public Job helloJob(JobRepository jobRepository, Step helloStep1) {
		return new JobBuilder("helloJob", jobRepository)
			.start(helloStep1)
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@JobScope
	@Bean
	public Step helloStep1(JobRepository jobRepository, Tasklet helloStep1Tasklet, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("helloStep1Tasklet", jobRepository)
			.tasklet(helloStep1Tasklet, platformTransactionManager)
			.build();
	}

	@StepScope
	@Bean
	public Tasklet helloStep1Tasklet() {
		return ((contribution, chunkContext) -> {
			System.out.println("Hello World 1-1");
			return RepeatStatus.FINISHED;
		});
	}
}