package com.instagram.news.service;

import com.instagram.news.domain.Post;
import com.instagram.news.domain.SlicedResult;

import java.util.Optional;

public interface NewsFeedService {
    SlicedResult<Post> getUserFeed(String username, Optional<String> pagingState);
}
