package com.example.practicesbb.batch;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.practicesbb.domain.product.Product;
import com.example.practicesbb.domain.product.ProductLog;
import com.example.practicesbb.domain.product.ProductLogRepository;
import com.example.practicesbb.domain.product.ProductRepository;
import com.example.practicesbb.standard.Ut;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MakeProductLogJobConfig {
	private final int CHUNK_SIZE = 10;
	private final ProductRepository productRepository;
	private final ProductLogRepository productLogRepository;

	@Bean
	public Job makeProductLogJob(JobRepository jobRepository, Step makeProductLogStep1) {
		return new JobBuilder("makeProductLogJob", jobRepository)
			.start(makeProductLogStep1)
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@JobScope
	@Bean
	public Step makeProductLogStep1(
		JobRepository jobRepository,
		RepositoryItemReader<Product> step1Reader,
		ItemProcessor<Product, ProductLog> step1Processor,
        ItemWriter<ProductLog> step1Writer,
		PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("makeProductLogStep1Tasklet1", jobRepository)
			.<Product, ProductLog>chunk(CHUNK_SIZE, platformTransactionManager)
			.reader(step1Reader)
			.processor(step1Processor)
			.writer(step1Writer)
			.build();
	}

	@StepScope
	@Bean
	public RepositoryItemReader<Product> step1Reader(
		@Value("#{jobParameters['startDate']}") String _startDate,
		@Value("#{jobParameters['endDate']}") String _endDate
		) {
		LocalDateTime startDate = Ut.Date.parse(_startDate);
		LocalDateTime endDate = Ut.Date.parse(_endDate);

		return new RepositoryItemReaderBuilder<Product>()
			.name("step1Reader")
			.repository(productRepository)
			.methodName("findByCreatedDateBetween")
			.pageSize(CHUNK_SIZE)
			.arguments(Arrays.asList(startDate, endDate))
			.sorts(Collections.singletonMap("id", Sort.Direction.ASC))
			.build();
	}

	@StepScope
	@Bean
	public ItemProcessor<Product, ProductLog> step1Processor() {
		return product -> ProductLog.builder()
				.product(product)
				.name(product.getName())
				.build();
	}

	@StepScope
	@Bean
	public ItemWriter<ProductLog> step1Writer() {
		return items -> items.forEach(productLogRepository::save);
	}
}
