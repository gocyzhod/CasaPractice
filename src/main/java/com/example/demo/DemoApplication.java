package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
//@EnableJpaRepositories(basePackages = {"com.example.demo.Repository"})
public class DemoApplication {

	public static void main(String[] args) {


		SpringApplication.run(DemoApplication.class, args);


	}

}
