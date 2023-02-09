package com.enterprise.batchfileprocessing.configurations;

import com.enterprise.batchfileprocessing.entity.FileStructure;
import com.enterprise.batchfileprocessing.repository.FileProcessingRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.net.URL;

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
@AllArgsConstructor
public class SpringBatchConfiguration {
   private static final String DELIMITER = ",";
   private final JobBuilder jobBuilderFactory;
   private final StepBuilder stepBuilder;
   private final FileProcessingRepository fileProcessingRepository;

   @Bean
   FlatFileItemReader<FileStructure> reader(){
      FlatFileItemReader<FileStructure> itemReader= new FlatFileItemReader<>();
      URL resource = this.getClass().getResource("CARD_NUMBER_EXTRACT_MT.GPTAASMerchantD128.TIME.csv");
      assert resource != null;
      itemReader.setResource(new FileSystemResource(resource.getPath()));
      itemReader.setName("csvReader");
      itemReader.setLinesToSkip(1);     //Contains the header that's why skipping.
      itemReader.setLineMapper(lineMapper());
      return itemReader;
   }

   @Bean
   private LineMapper<FileStructure> lineMapper() {
      DefaultLineMapper<FileStructure> lineMapper = new DefaultLineMapper<>();
      DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
      lineTokenizer.setDelimiter(DELIMITER);


     lineTokenizer.setNames("guid","sensitiveData");

      BeanWrapperFieldSetMapper<FileStructure> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
      fieldSetMapper.setTargetType(FileStructure.class);

      lineMapper.setLineTokenizer(lineTokenizer);
      lineMapper.setFieldSetMapper(fieldSetMapper);
      return lineMapper;
   }


}
