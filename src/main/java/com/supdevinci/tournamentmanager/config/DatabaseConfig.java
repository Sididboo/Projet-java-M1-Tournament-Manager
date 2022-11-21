package com.supdevinci.tournamentmanager.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.supdevinci.tournamentmanager.constant.EnvConstant;

/*
 * Define database config
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
        "com.supdevinci.tournamentmanager.repository"
})
public class DatabaseConfig {

    @Primary
    @Bean
    public DataSource dataSource() {
        boolean production = false; // H2Database by default

        if (System.getenv(EnvConstant.PRODUCTION) != null &&
                System.getenv(EnvConstant.PRODUCTION).equals("prod")) {
            production = true;
        }

        // Either Mysql or H2 Database depending on production variable
        if (production) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://" + System.getenv(EnvConstant.MYSQL_HOST) + ":"
                    + System.getenv(EnvConstant.MYSQL_PORT) + "/"
                    + System.getenv(EnvConstant.MYSQL_DATABASE) + "?allowPublicKeyRetrieval=true&useSSL=false");
            dataSource.setUsername(System.getenv(EnvConstant.MYSQL_USERNAME));
            dataSource.setPassword(System.getenv(EnvConstant.MYSQL_PASSWORD));

            return dataSource;

        } else {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.h2.Driver");
            // name of database : tournamentmanager
            dataSource.setUrl("jdbc:h2:mem:tournamentmanager;DB_CLOSE_DELAY=-1");

            return dataSource;
        }

    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.supdevinci.tournamentmanager.*");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    Properties additionalProperties() {
        boolean production = false;

        if (System.getenv(EnvConstant.PRODUCTION) != null &&
                System.getenv(EnvConstant.PRODUCTION).equals("prod")) {
            production = true;
        }

        // Add the properties according to the selected database
        if (production) {
            Properties properties = new Properties();
            properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            properties.setProperty("hibernate.show_sql", "true");

            return properties;

        } else {
            Properties properties = new Properties();
            properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            properties.setProperty("hibernate.show_sql", "true");

            return properties;
        }
    }

}
