package com.prolog.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.prolog.desafio.repository")
@ComponentScan(basePackages = "com.prolog.desafio")
@EntityScan(basePackages = "com.prolog.desafio.model")
@SpringBootApplication
public class PrologApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrologApplication.class, args);
	}

}
