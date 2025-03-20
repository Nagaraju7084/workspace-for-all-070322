package com.backend.postgresql.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//enable transaction management
@EnableTransactionManagement
//enable jpa repositories
@EnableJpaRepositories(
		//add entity manager ref
		entityManagerFactoryRef = "secondLocalContainerEntityManagerFactoryBean",
		//scan the repositories package
		basePackages = {"com.backend.postgresql.repositories"},
		//add transaction manager ref
		transactionManagerRef = "secondPlatformTransactionManager"
		)
public class PostgresqlConfig {
	
	//take the help of environent ref to read the properties file by autowiring
	@Autowired
	private Environment environment;
	
	//data source
	@Bean(name = "secondDataSource")
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("second.datasource.url"));
		dataSource.setUsername(environment.getProperty("second.datasource.username"));
		dataSource.setPassword(environment.getProperty("second.datasource.password"));
		return dataSource;
	}
	
	//entity manager factory bean
	@Bean(name = "secondLocalContainerEntityManagerFactoryBean")
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		//set data source
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		
		//set jpa vendor adapter
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		
		//construct a map and add hibernate/jpa properties and set to localContainerEntityManagerFactoryBean
		Map<String, String> jpaProperties = new HashMap<>();
		
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		
		//set the props
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
		
		//set packages to scan : scan the entities package
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.backend.postgresql.entities");
		
		
		return localContainerEntityManagerFactoryBean;
	}
	
	//transaction manager
	@Bean(name = "secondPlatformTransactionManager")
	@Primary
	public PlatformTransactionManager platformTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}
}
