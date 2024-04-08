// package com.example.pos.connection2.config;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;
// import jakarta.persistence.EntityManagerFactory;
// import javax.sql.DataSource;

// @Configuration
// @EnableTransactionManagement
// @EnableJpaRepositories(
//         entityManagerFactoryRef = "DataStor2EntityManagerFactory",
//         transactionManagerRef = "Database2TransactionManager",
//         basePackages = {"com.example.pos.connection2.repository"}
// )
// public class ConnectionDatabase2 {

 
//      @Bean(name = "dataSource2")
//      @ConfigurationProperties(prefix = "db2.datasource")
//      public DataSource dataSource2() {
//           return DataSourceBuilder.create().build();
//      }

 
//      @Bean(name = "DataStor2EntityManagerFactory")
//      public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//                EntityManagerFactoryBuilder builder,
//                @Qualifier("dataSource2") DataSource dataSource) {
//           return builder
//                     .dataSource(dataSource)
//                     .packages("com.example.pos.connection2.entity")
//                     .persistenceUnit("db2")
//                     .build();
//      }

 
//      @Bean(name = "Database2TransactionManager")
//      public PlatformTransactionManager Database2TransactionManager(
//                @Qualifier("DataStor2EntityManagerFactory") EntityManagerFactory DataStor2EntityManagerFactory) {
//           return new JpaTransactionManager(DataStor2EntityManagerFactory);
//      }
// }
