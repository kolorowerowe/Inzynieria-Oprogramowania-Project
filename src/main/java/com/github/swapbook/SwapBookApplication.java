package com.github.swapbook;

import com.github.swapbook.email.EmailService;
import com.github.swapbook.email.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwapBookApplication {

    private static final Logger log = LoggerFactory.getLogger(SwapBookApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SwapBookApplication.class, args);
        log.info("SwapBookApplication has just started");

    }

}
