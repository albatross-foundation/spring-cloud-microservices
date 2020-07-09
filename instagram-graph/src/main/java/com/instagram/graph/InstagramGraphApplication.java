package com.instagram.graph;

import com.instagram.graph.message.UserEventStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(UserEventStream.class)
public class InstagramGraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramGraphApplication.class, args);
    }

}
