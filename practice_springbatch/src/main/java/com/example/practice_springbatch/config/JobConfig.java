package com.example.practice_springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
public class JobConfig {
	@Bean
	public Job job(JobRepository jobRepository, Step step) {
		return new JobBuilder("hellojob", jobRepository) // yml의 job.name 설정에 맞는 job만 실행
			.start(step)
			.build();
	}
	@Bean
	public Step step(JobRepository jobRepository, Tasklet stepTasklet, PlatformTransactionManager platformTransactionManager){
		return new StepBuilder("stepTasklet", jobRepository)
			.tasklet(stepTasklet, platformTransactionManager).build();
	}
	@Bean
	public Tasklet stepTasklet(){
		return ((contribution, chunkContext) -> {
			log.info("hello world");
			return RepeatStatus.FINISHED;
		});
	}
}