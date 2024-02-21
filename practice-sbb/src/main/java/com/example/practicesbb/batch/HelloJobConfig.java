package com.example.practicesbb.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class HelloJobConfig {
	@Bean
	public Job helloJob(JobRepository jobRepository, Step helloStep1) {
		return new JobBuilder("helloJob", jobRepository)
			.start(helloStep1)
			.build();
	}

	@JobScope
	@Bean
	public Step helloStep1(JobRepository jobRepository, Tasklet helloStep1Tasklet1, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("helloStep1Tasklet1", jobRepository)
			.tasklet(helloStep1Tasklet1, platformTransactionManager)
			.build();
	}

	@StepScope
	@Bean
	public Tasklet helloStep1Tasklet1() {
		return ((contribution, chunkContext) -> {
			log.info("Hello World 1/1");
			return RepeatStatus.FINISHED;
		});
	}
}
