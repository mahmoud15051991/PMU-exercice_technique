package com.exercice.pmu_processor;

import com.exercice.pmu_datamodel.models.Course;
import com.exercice.pmu_processor.processors.StorageCourseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class PmuProcessorApplication {

	@Autowired
	StorageCourseProcessor storageCourseProcessor;

	public static void main(String[] args) {
		SpringApplication.run(PmuProcessorApplication.class, args);
	}

	@Bean
	public Function<KStream<String, Course>,
			KStream<String, Course>> writeCourseIntoDatabase(){

		return stringCourseKStream -> stringCourseKStream
				.mapValues((s, course) -> storageCourseProcessor.persistCourseIntoDatabase(course));
	}

}
