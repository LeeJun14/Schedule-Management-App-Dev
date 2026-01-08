package com.example.schedulemanagementappdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleManagementAppDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagementAppDevApplication.class, args);
    }

}
