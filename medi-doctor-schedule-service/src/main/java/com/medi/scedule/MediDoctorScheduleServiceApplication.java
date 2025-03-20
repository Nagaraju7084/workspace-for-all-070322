package com.medi.scedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class MediDoctorScheduleServiceApplication implements CommandLineRunner{

	@Autowired
	private HikariDataSource hikariDataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(MediDoctorScheduleServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("maximum connections : "+hikariDataSource.getMaximumPoolSize());
		System.out.println("hikari cp datasource properties are:\t");
		for(Object key : hikariDataSource.getDataSourceProperties().keySet()) {
			System.out.println(hikariDataSource.getDataSourceProperties().get(key));
		}
		System.out.println("connection object live in active pool : "+hikariDataSource.getKeepaliveTime());
		System.out.println("connection object fail to intialize : "+hikariDataSource.getInitializationFailTimeout());
		System.out.println("connection object life time in active pool : "+hikariDataSource.getMaxLifetime());
	}

}
