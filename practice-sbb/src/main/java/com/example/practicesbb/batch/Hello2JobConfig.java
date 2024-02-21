package com.example.practicesbb.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class Hello2JobConfig {
	@Bean
	public Job hello2Job(JobRepository jobRepository, Step hello2Step1, Step hello2Step2) {
		return new JobBuilder("hello2Job", jobRepository)
			.start(hello2Step1)
			.next(hello2Step2)
			.build();
	}

	@Bean
	public Step hello2Step1(JobRepository jobRepository, Tasklet hello2Step1Tasklet1, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("hello2Step1Tasklet1", jobRepository)
			.tasklet(hello2Step1Tasklet1, platformTransactionManager)
			.build();
	}

	@Bean
	public Tasklet hello2Step1Tasklet1() {
		return ((contribution, chunkContext) -> {
			log.info("Hello World 1/2");
			return RepeatStatus.FINISHED;
		});
	}

	@Bean
	public Step hello2Step2(JobRepository jobRepository, Tasklet hello2Step2Tasklet1, PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("hello2Step2Tasklet1", jobRepository)
			.tasklet(hello2Step2Tasklet1, platformTransactionManager)
			.build();
	}

	@Bean
	public Tasklet hello2Step2Tasklet1() {
		return ((contribution, chunkContext) -> {
			log.info("Hello World 2/2");
			return RepeatStatus.FINISHED;
		});
	}
}
