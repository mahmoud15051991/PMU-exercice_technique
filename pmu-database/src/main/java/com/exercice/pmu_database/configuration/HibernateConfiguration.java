package com.exercice.pmu_database.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfiguration {

    @Value("${config.driverClassName}")
    private String DRIVER;

    @Value("${config.postgres.username}")
    private String USERNAME;

    @Value("${config.postgres.password}")
    private String PASSWORD;

    @Value("${config.url}")
    private String URL;

    @Value("${config.hibernate.dialect}")
    private String DIALECT;

    @Value("${config.hibernate.show_sql}")
    private String SHOW_SQL;

    @Value("${config.hbm2ddl.auto}")
    private String HBM2DDL_AUTO;

    @Value("${config.driverClassName}")
    private String PACKAGES_TO_SCAN;

    //    spring.jpa.generate-ddl=true

    @Bean
    public DataSource dataSource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean sessionFactory(){

        LocalContainerEntityManagerFactoryBean sessionFactory = new LocalContainerEntityManagerFactoryBean();

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
        //sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);

        sessionFactory.setJpaProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(sessionFactory().getObject());

        return transactionManager;
    }

}
