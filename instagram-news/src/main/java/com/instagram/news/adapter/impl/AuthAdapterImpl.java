package com.instagram.news.adapter.impl;

import com.instagram.news.adapter.AuthAdapter;
import com.instagram.news.client.AuthServiceClient;
import com.instagram.news.config.JwtConfig;
import com.instagram.news.domain.JwtAuthenticationResponse;
import com.instagram.news.domain.ServiceLoginRequest;
import com.instagram.news.domain.UserSummary;
import com.instagram.news.exception.UnableToGetAccessTokenException;
import com.instagram.news.exception.UnableToGetUsersException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class AuthAdapterImpl implements AuthAdapter {

    @Autowired
    private AuthServiceClient authClient;
    @Autowired
    private ServiceLoginRequest serviceLoginRequest;
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public String getAccessToken() {
        ResponseEntity<JwtAuthenticationResponse> response =
                authClient.signin(serviceLoginRequest);

        if (!response.getStatusCode().is2xxSuccessful()) {
            String message = String.format("unable to get access token for service account, %s",
                    response.getStatusCode());

            log.error(message);
            throw new UnableToGetAccessTokenException(message);
        }

        return response.getBody().getAccessToken();
    }

    @Override
    public Map<String, String> usersProfilePic(String token, List<String> usernames) {
        ResponseEntity<List<UserSummary>> response =
                authClient.findByUsernameIn(
                        jwtConfig.getPrefix() + token, usernames);
        if (!response.getStatusCode().is2xxSuccessful()) {
            String message = String.format("unable to get user summaries %s",
                    response.getStatusCode());

            log.error(message);
            throw new UnableToGetUsersException(message);
        }

        return response
                .getBody()
                .stream()
                .collect(toMap(UserSummary::getUsername,
                        UserSummary::getProfilePicture));
    }
}
