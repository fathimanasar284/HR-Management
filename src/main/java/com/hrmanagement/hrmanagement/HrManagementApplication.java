package com.hrmanagement.hrmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.hrmanagement.hrmanagement.dao")
@EntityScan("com.hrmanagement.hrmanagement.model")
public class HrManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrManagementApplication.class, args);
    }
}
