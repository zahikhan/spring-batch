package com.enterprise.batchfileprocessing.configurations;

import com.enterprise.batchfileprocessing.entities.FileStructure;
import com.enterprise.batchfileprocessing.repository.FileProcessingRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
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
@AllArgsConstructor
public class SpringBatchConfiguration extends DefaultBatchConfiguration {
    private static final String DELIMITER = "|";
    private final FileProcessingRepository fileRepository;

    private DataSource dataSource;

    @Bean
    @SneakyThrows
    @Override
    public JobRepository jobRepository() {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setDatabaseType(DatabaseType.MYSQL.getProductName());
        factory.setTransactionManager(transactionManager());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    FlatFileItemReader<FileStructure> reader() {
        FlatFileItemReader<FileStructure> itemReader = new FlatFileItemReader<>();
        URL resource = getClass().getResource("/CARD_NUMBER_EXTRACT_MT.GPTAASMerchantD128.TIME.csv");
        assert resource != null;
        itemReader.setResource(new FileSystemResource(resource.getPath()));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);     //Contains the header that's why skipping.
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    LineMapper<FileStructure> lineMapper() {
        DefaultLineMapper<FileStructure> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(DELIMITER);


        lineTokenizer.setNames("guid", "sensitiveData");

        BeanWrapperFieldSetMapper<FileStructure> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(FileStructure.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    FileRowProcessor processor() {
        return new FileRowProcessor();
    }

    @Bean
    RepositoryItemWriter<FileStructure> writer() {
        RepositoryItemWriter<FileStructure> writer = new RepositoryItemWriter<>();
        writer.setRepository(fileRepository);
        return writer;
    }

    @Bean
    Step step1() {
        PlatformTransactionManager transactionManager = transactionManager();
        StepBuilder stepBuilder = new StepBuilder("transform", jobRepository());
        return stepBuilder
                .<FileStructure, FileStructure>chunk(1, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
//                .taskExecutor(taskExecutor())
                .build();
    }





    @Bean
    Job job( ) {
        JobBuilder jobBuilderFactory = new JobBuilder("CARD_EXTRACT_MT", jobRepository());
        return jobBuilderFactory.flow(step1()).end()
                .build();
    }

/*    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }*/
}

