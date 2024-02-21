package com.example.practicesbb.batch;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class Hello5JobConfig {
	@Bean
	public Job hello5Job(JobRepository jobRepository, Step hello5Step1) {
		return new JobBuilder("hello5Job", jobRepository)
			.start(hello5Step1)
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@JobScope
	@Bean
	public Step hello5Step1(
		JobRepository jobRepository,
		PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("hello5Step1Tasklet1", jobRepository)
			.<Integer, String>chunk(10, platformTransactionManager)
			.reader(() -> {
				int no = (int)(Math.random() * 500);
				if (no == 100)
					return null;
				return no;
			})
			.processor(
				item -> "No. " + item
			)
			.writer(chunk -> {
				List<? extends String> items = chunk.getItems();

				for (String item : items) {
					log.info("item = " + item);
				}
			})
			.build();

	}
}
