// package com.example.pos.config.DataSourceConfig;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;

// import jakarta.activation.DataSource;
// import jakarta.persistence.EntityManagerFactory;

// public class Database2Config {
//      @Bean(name = "dataSource2")
//      @ConfigurationProperties(prefix = "spring.datasource.db2")
//      public DataSource dataSource2() {
//           return DataSourceBuilder.create().build();
//      }

//      @Bean(name = "entityManagerFactory2")
//      public LocalContainerEntityManagerFactoryBean entityManagerFactory2(EntityManagerFactoryBuilder builder,
//                @Qualifier("dataSource2") DataSource dataSource) {
//           return builder
//                     .dataSource(dataSource)
//                     .packages("com.example.db2.models")
//                     .persistenceUnit("db2")
//                     .build();
//      }

//      @Bean(name = "transactionManager2")
//      public PlatformTransactionManager transactionManager2(
//                @Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory) {
//           return new JpaTransactionManager(entityManagerFactory);
//      }
// }
