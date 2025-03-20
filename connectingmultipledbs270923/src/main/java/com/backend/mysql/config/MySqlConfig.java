package com.backend.mysql.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityMangerManagerFactoryBean",
		basePackages = {"com.backend.mysql.repositories"},
		transactionManagerRef = "platformTransactionManager"
		)
public class MySqlConfig {
	
	@Autowired
	private Environment environment;
	
	
	//data source
	@Bean
	@Primary
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		
		return dataSource;
	}
	
	//entity manager factory
	@Bean(name = "entityMangerManagerFactoryBean")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityMangerManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		//set datasource
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		
		//set jpa vendor adapter
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		
		//construct a map which holding jpaProperties/hibernate properties and set map to localContainerEntityManagerFactoryBean
		Map<String, String> jpaProperties = new HashMap<>();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
		
		//scan the entities package where our entities present
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.backend.mysql.entities");
		
		return localContainerEntityManagerFactoryBean;
	}
	
	
	//transaction manager factory
	@Bean(name = "platformTransactionManager")
	@Primary
	public PlatformTransactionManager platformTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		//set entitymanagerfactorybean
		jpaTransactionManager.setEntityManagerFactory(entityMangerManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}
	
}
