package com.example.fabrick_exercise_carignano.config;

import com.example.fabrick_exercise_carignano.fabrickservice.client.ClientService;
import com.example.fabrick_exercise_carignano.localservice.batch.TransactionBatchService;
import com.example.fabrick_exercise_carignano.localservice.image.FileService;
import com.example.fabrick_exercise_carignano.repositories.csv.TransactionCsv;
import com.example.fabrick_exercise_carignano.repositories.dao.Transaction;
import com.example.fabrick_exercise_carignano.schedulers.JobCompletionListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.fabrick_exercise_carignano.repositories")
@EnableBatchProcessing
public class FabrickConfig {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final JobLauncher jobLauncher;
    private final TransactionBatchService transactionBatchService;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final FileService fileService;

    public FabrickConfig(JobLauncher jobLauncher,
                         TransactionBatchService transactionBatchService,
                         JobRepository jobRepository,
                         PlatformTransactionManager transactionManager,
                         FileService fileService){
        this.jobLauncher = jobLauncher;
        this.transactionBatchService = transactionBatchService;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.fileService = fileService;
    }

    @Bean
    public RestClient restClient(@Value("${fabrick.base-url}") String baseUrl, @Value("${fabrick.auth-schema}") String authSchema, @Value("${fabrick.api-key}") String apiKey){
        return RestClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .defaultHeader("Api-Key", apiKey)
                .defaultHeader("Auth-Schema", authSchema)
                .requestInterceptor((request, body, execution) -> {
                    log.info("Serialized body: " + new String(body, StandardCharsets.UTF_8));
                    return execution.execute(request, body);
                })
                .build();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Transaction> transactionReader() {
        return transactionBatchService.createReader();
    }

    @Bean
    public ItemProcessor<Transaction, TransactionCsv> transactionProcessor() {
        return transactionBatchService.transactionProcessor();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<TransactionCsv> transactionWriter() {
        return transactionBatchService.createWriter();
    }

    @Bean
    public Step transactionStep() {
        return new StepBuilder("transactionStep", jobRepository)
                .<Transaction, TransactionCsv>chunk(50, transactionManager)
                .reader(transactionReader())
                .processor(transactionProcessor())
                .writer(transactionWriter())
                .build();
    }

    // Job definition
    @Bean
    public Job transactionJob() {
        return new JobBuilder("transactionJob", jobRepository)
                .start(transactionStep())
                .listener(jobCompletionListener())
                .build();
    }


    @Bean
    public JobCompletionListener jobCompletionListener() {
        return new JobCompletionListener(fileService);
    }

}
