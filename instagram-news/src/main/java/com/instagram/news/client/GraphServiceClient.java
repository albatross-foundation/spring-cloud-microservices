package com.instagram.news.client;

import com.instagram.news.domain.PagedResult;
import com.instagram.news.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "INSTAGRAM-GRAPH")
public interface GraphServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/paginated/{username}/followers")
    ResponseEntity<PagedResult<User>> findFollowers(
            @RequestHeader("Authorization") String token,
            @PathVariable String username,
            @RequestParam int page,
            @RequestParam int size);
}
