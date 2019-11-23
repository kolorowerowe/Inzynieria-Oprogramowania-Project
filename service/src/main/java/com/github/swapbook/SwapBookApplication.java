package com.github.swapbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SwapBookApplication {

	private static final Logger log = LoggerFactory.getLogger(SwapBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SwapBookApplication.class, args);

		log.info("SwapBookApplication has just started");
	}


}
