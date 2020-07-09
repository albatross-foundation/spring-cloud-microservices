package com.instagram.news;

import com.instagram.news.message.PostEventStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(PostEventStream.class)
@EnableFeignClients
public class InstagramNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramNewsApplication.class, args);
    }

}
