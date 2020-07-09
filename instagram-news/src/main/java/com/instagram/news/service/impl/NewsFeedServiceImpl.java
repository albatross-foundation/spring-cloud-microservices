package com.instagram.news.service.impl;

import com.datastax.driver.core.PagingState;
import com.instagram.news.adapter.AuthAdapter;
import com.instagram.news.adapter.PostAdapter;
import com.instagram.news.domain.Post;
import com.instagram.news.domain.SlicedResult;
import com.instagram.news.entity.UserNewsFeed;
import com.instagram.news.exception.ResourceNotFoundException;
import com.instagram.news.repository.NewsFeedRepository;
import com.instagram.news.service.NewsFeedService;
import com.instagram.news.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class NewsFeedServiceImpl implements NewsFeedService {

    @Autowired
    private NewsFeedRepository newsFeedRepository;
    @Autowired
    private PostAdapter postService;
    @Autowired
    private AuthAdapter authService;

    @Override
    public SlicedResult<Post> getUserFeed(String username, Optional<String> pagingState) {
        log.info("getting feed for user {} isFirstPage {}", username, pagingState.isEmpty());

        CassandraPageRequest request = pagingState
                .map(pState -> CassandraPageRequest
                        .of(PageRequest.of(0, AppConstants.PAGE_SIZE), PagingState.fromString(pState)))
                .orElse(CassandraPageRequest.first(AppConstants.PAGE_SIZE));

        Slice<UserNewsFeed> page =
                newsFeedRepository.findByUsername(username, request);

        if (page.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format("Feed not found for user %s", username));
        }

        String pageState = null;

        if (!page.isLast()) {
            pageState = ((CassandraPageRequest) page.getPageable())
                    .getPagingState().toString();
        }

        List<Post> posts = getPosts(page);

        return SlicedResult
                .<Post>builder()
                .content(posts)
                .isLast(page.isLast())
                .pagingState(pageState)
                .build();
    }

    private List<Post> getPosts(Slice<UserNewsFeed> page) {

        String token = authService.getAccessToken();

        List<String> postIds = page.stream()
                .map(feed -> feed.getPostId())
                .collect(toList());

        List<Post> posts = postService.findPostsIn(token, postIds);
        List<String> usernames = posts.stream()
                .map(Post::getUsername)
                .distinct()
                .collect(toList());
        Map<String, String> usersProfilePics =
                authService.usersProfilePic(token, new ArrayList<>(usernames));
        posts.forEach(post -> post.setUserProfilePic(
                usersProfilePics.get(post.getUsername())));

        return posts;
    }
}
