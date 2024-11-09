package com.burglak.linker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
@EnableAsync
public class LinkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkerApplication.class, args);
	}

}
