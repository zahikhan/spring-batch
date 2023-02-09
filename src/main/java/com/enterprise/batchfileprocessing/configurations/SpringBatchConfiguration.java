package com.enterprise.batchfileprocessing.configurations;

import com.enterprise.batchfileprocessing.repository.FileProcessingRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * This class is for
 * <p>
 * Project Name: batchfileprocessing
 *
 * @author Zahid Khan
 * @Time 2/9/2023
 * @since 1.0
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {
   private JobBuilder jobBuilderFactory;
   private StepBuilder stepBuilder;
   private FileProcessingRepository fileProcessingRepository;



}
