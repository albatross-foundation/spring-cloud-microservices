package com.instagram.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoAuditing
public class InstagramMediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramMediaApplication.class, args);
    }

}
