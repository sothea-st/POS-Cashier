package com.example.pos.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "DataStor1EntityManagerFactory",
        transactionManagerRef = "Database1TransactionManager",
        basePackages = {"com.example.pos.repository"}
)
public class ConnectionDatabase1 {

    @Primary
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "DataStor1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource1") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.pos.entity")
                .persistenceUnit("db1")
                .build();
    }

    @Primary
    @Bean(name = "Database1TransactionManager")
    public PlatformTransactionManager Database1TransactionManager(
            @Qualifier("DataStor1EntityManagerFactory") EntityManagerFactory
                    DataStor1EntityManagerFactory
    ) {
        return new JpaTransactionManager(DataStor1EntityManagerFactory);
    }
}
