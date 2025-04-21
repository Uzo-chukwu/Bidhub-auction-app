package com.bidhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BidHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(BidHubApplication.class, args);
    }
}
