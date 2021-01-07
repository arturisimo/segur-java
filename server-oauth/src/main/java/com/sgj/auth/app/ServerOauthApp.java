package com.sgj.auth.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.sgj.auth.controller", "com.sgj.auth.service","com.sgj.auth.config"})
@EntityScan(basePackages = {"com.sgj.auth.model"})
@EnableJpaRepositories(basePackages = {"com.sgj.auth.repository"})
@SpringBootApplication
public class ServerOauthApp {

	public static void main(String[] args) {
		SpringApplication.run(ServerOauthApp.class, args);
	}

}