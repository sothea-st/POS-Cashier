// package com.example.pos.config.DataSourceConfig;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// import jakarta.activation.DataSource;
// import jakarta.persistence.EntityManagerFactory;
// @Configuration
// @EnableTransactionManagement
// public class Database1Config {
//            @Bean(name = "dataSource1")
//     @ConfigurationProperties(prefix = "spring.datasource.db1")
//     public DataSource dataSource1() {
//         return DataSourceBuilder.create().build();
//     }

//     @Bean(name = "entityManagerFactory1")
//     public LocalContainerEntityManagerFactoryBean entityManagerFactory1(EntityManagerFactoryBuilder builder,
//                                                                           @Qualifier("dataSource1") DataSource dataSource) {
//         return builder
//                 .dataSource(dataSource)
//                 .packages("com.example.db1.models")
//                 .persistenceUnit("db1")
//                 .build();
//     }

//     @Bean(name = "transactionManager1")
//     public PlatformTransactionManager transactionManager1(
//             @Qualifier("entityManagerFactory1") EntityManagerFactory entityManagerFactory) {
//         return new JpaTransactionManager(entityManagerFactory);
//     }
// }
