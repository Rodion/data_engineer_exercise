package com.juno.multidatasources;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "postgreSQLEntityManagerFactory", transactionManagerRef = "postgreSQLTransactionManager", basePackages = "com.juno.repository")
public class PostgreDBConfig {
	@Autowired
	private Environment env;

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties postgreDBDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource postgreDBDataSource() {
		DataSourceProperties dataSourceProperties = postgreDBDataSourceProperties();
		return DataSourceBuilder.create().driverClassName(dataSourceProperties.getDriverClassName())
				.url(dataSourceProperties.getUrl()).username(dataSourceProperties.getUsername())
				.password(dataSourceProperties.getPassword()).build();
	}

	@Bean
	public PlatformTransactionManager postgreSQLTransactionManager() {
		EntityManagerFactory factory = postgreSQLEntityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean postgreSQLEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(postgreDBDataSource());
		factory.setPackagesToScan(new String[] { "com.juno.entity" });
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
		factory.setJpaProperties(jpaProperties);
		return factory;
	}

	@Bean
	public DataSourceInitializer postgreSQLSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(postgreDBDataSource());
		return dataSourceInitializer;
	}
}
