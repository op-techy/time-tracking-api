package com.codewithope.timetrackingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TimeTrackingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeTrackingApiApplication.class, args);
    }

}
