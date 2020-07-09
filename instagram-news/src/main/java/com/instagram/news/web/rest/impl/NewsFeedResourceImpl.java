package com.instagram.news.web.rest.impl;

import com.instagram.news.domain.Post;
import com.instagram.news.domain.SlicedResult;
import com.instagram.news.service.NewsFeedService;
import com.instagram.news.web.rest.NewsFeedResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class NewsFeedResourceImpl implements NewsFeedResource {

    @Autowired
    private NewsFeedService newsFeedService;

    @Override
    public ResponseEntity<SlicedResult<Post>> getFeed(String username, Optional<String> pagingState) {
        log.info("fetching feed for user {} isFirstPage {}", username, pagingState.isEmpty());
        return ResponseEntity.ok(newsFeedService.getUserFeed(username, pagingState));
    }
}
