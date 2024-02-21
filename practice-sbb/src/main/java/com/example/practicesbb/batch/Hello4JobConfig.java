package com.example.practicesbb.batch;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class Hello4JobConfig {
	@Bean
	public Job hello4Job(JobRepository jobRepository, Step hello4Step1) {
		return new JobBuilder("hello4Job", jobRepository)
			.start(hello4Step1)
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@JobScope
	@Bean
	public Step hello4Step1(
		JobRepository jobRepository,
		hello4Step1Reader hello4Step1Reader,
		hello4Step1Processor hello4Step1Processor,
		hello4Step1Writer hello4Step1Writer,
		PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("hello4Step1Tasklet1", jobRepository)
			.<Integer, String>chunk(10, platformTransactionManager)
			.reader(hello4Step1Reader)
			.processor(hello4Step1Processor)
			.writer(hello4Step1Writer)
			.build();

	}

	// 원본 데이터 읽기
	// 실패를 대비해서 배치가 완료되었다는 특정 칼럼을 만들던지 해서 지능적으로 실패한 부분만 재개가능하도록 짜야한다.
	@StepScope
	@Component
	public static class hello4Step1Reader implements ItemReader<Integer> {
		@Override
		public Integer read() {
			int no = (int)(Math.random() * 500); // return null을 하지 않으면 영원히 읽는다.
			if (no == 100)
				return null; // null 값을 통해 해당 데이터가 끝났음을 분기
			return no;
		}
	}

	// 원본 데이터 가공
	@StepScope
	@Component
	public static class hello4Step1Processor implements ItemProcessor<Integer, String> {

		@Override
		public String process(Integer item) throws Exception {
			return "no. " + item;
		}
	}

	// 가공된 데이터 쓰기
	@StepScope
	@Component
	public static class hello4Step1Writer implements ItemWriter<String> {

		@Override
		public void write(Chunk<? extends String> chunk) throws Exception {
			List<String> items = (List<String>)chunk.getItems();
			for (String item : items) {
				log.info("item = " + item);
			}
		}
	}
}
