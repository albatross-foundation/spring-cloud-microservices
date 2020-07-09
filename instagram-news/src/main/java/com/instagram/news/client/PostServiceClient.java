package com.instagram.news.client;

import com.instagram.news.domain.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "INSTAGRAM-POST")
public interface PostServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/in")
    ResponseEntity<List<Post>> findPostsByIdIn(
            @RequestHeader("Authorization") String token,
            @RequestBody List<String> ids);

}