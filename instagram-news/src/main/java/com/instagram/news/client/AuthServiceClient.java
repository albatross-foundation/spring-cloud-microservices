package com.instagram.news.client;

import com.instagram.news.domain.JwtAuthenticationResponse;
import com.instagram.news.domain.ServiceLoginRequest;
import com.instagram.news.domain.UserSummary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "INSTAGRAM-AUTH")
public interface AuthServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody ServiceLoginRequest request);

    @RequestMapping(method = RequestMethod.POST, value = "/summary/in")
    ResponseEntity<List<UserSummary>> findByUsernameIn(
            @RequestHeader("Authorization") String token,
            @RequestBody List<String> usernames);
}
