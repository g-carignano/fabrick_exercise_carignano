package com.example.fabrick_exercise_carignano.config;

import com.example.fabrick_exercise_carignano.fabrickservice.client.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.client.RestClient;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.fabrick_exercise_carignano.repositories")
public class FabrickConfig {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

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

    /*@Bean
    public DataSource dataSource(String dbDriver, String dbUrl, String dbUsername, String dbPassword) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setUrl(dbUrl);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(@Value("${spring.datasource.driver-class-name}") String dbDriver, @Value("${spring.datasource.url}") String dbUrl, @Value("${spring.datasource.username}") String dbUsername, @Value("${spring.datasource.password}") String dbPassword) {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource(dbDriver,dbUrl,dbUsername,dbPassword));

        // Packages where your entity classes are located
        factory.setPackagesToScan("com.example.entities");

        Properties hibernateProps = new Properties();
        hibernateProps.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); // change for your DB
        hibernateProps.put("hibernate.show_sql", "true");
        hibernateProps.put("hibernate.hbm2ddl.auto", "update");

        factory.setHibernateProperties(hibernateProps);
        return factory;
    }

    @Bean
    public org.springframework.orm.hibernate5.HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        org.springframework.orm.hibernate5.HibernateTransactionManager txManager = new org.springframework.orm.hibernate5.HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }*/
}
