package com.instagram.news.service.impl;

import com.instagram.news.adapter.AuthAdapter;
import com.instagram.news.client.GraphServiceClient;
import com.instagram.news.config.JwtConfig;
import com.instagram.news.domain.PagedResult;
import com.instagram.news.domain.Post;
import com.instagram.news.domain.User;
import com.instagram.news.entity.UserNewsFeed;
import com.instagram.news.exception.UnableToGetFollowersException;
import com.instagram.news.repository.NewsFeedRepository;
import com.instagram.news.service.NewsFeedGeneratorService;
import com.instagram.news.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsFeedGeneratorServiceImpl implements NewsFeedGeneratorService {

    @Autowired
    private AuthAdapter tokenService;
    @Autowired
    private GraphServiceClient graphClient;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private NewsFeedRepository repository;

    @Override
    public void addToFeed(Post post) {
        log.info("adding post {} to feed for user {}",
                post.getUsername(), post.getId());

        String token = tokenService.getAccessToken();

        boolean isLast = false;
        int page = 0;
        int size = AppConstants.PAGE_SIZE;

        while (!isLast) {

            ResponseEntity<PagedResult<User>> response =
                    graphClient.findFollowers(jwtConfig.getPrefix() + token, post.getUsername(), page, size);

            if (response.getStatusCode().is2xxSuccessful()) {

                PagedResult<User> result = response.getBody();

                log.info("found {} followers for user {}, page {}",
                        result.getTotalElements(), post.getUsername(), page);

                result.getContent()
                        .stream()
                        .map(user -> convertTo(user, post))
                        .forEach(repository::insert);

                isLast = result.isLast();
                page++;

            } else {
                String message =
                        String.format("unable to get followers for user %s", post.getUsername());

                log.warn(message);
                throw new UnableToGetFollowersException(message);
            }
        }
    }

    private UserNewsFeed convertTo(User user, Post post) {
        return UserNewsFeed
                .builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .postId(post.getId())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
