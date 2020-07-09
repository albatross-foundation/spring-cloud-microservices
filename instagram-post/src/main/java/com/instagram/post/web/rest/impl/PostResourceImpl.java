package com.instagram.post.web.rest.impl;

import com.instagram.post.domain.ApiResponse;
import com.instagram.post.domain.PostRequest;
import com.instagram.post.domain.PostResponse;
import com.instagram.post.service.PostService;
import com.instagram.post.web.rest.PostResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class PostResourceImpl implements PostResource {

    private final PostService postService;

    public PostResourceImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public ResponseEntity<?> createPost(PostRequest postRequest) {
        PostResponse postResponse = postService.createPost(postRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/post/create/{id}")
                .buildAndExpand(postResponse.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Post created successfully"));
    }

    @Override
    public void deletePost(String id, Principal user) {
        postService.deletePost(id, user.getName());
    }

    @Override
    public ResponseEntity<?> findCurrentUserPosts(Principal user) {
        List<PostResponse> postResponses = postService.postsByUsername(user.getName());
        return ResponseEntity.ok(postResponses);
    }

    @Override
    public ResponseEntity<?> findUserPosts(String userName) {
        List<PostResponse> postResponses = postService.postsByUsername(userName);
        return ResponseEntity.ok(postResponses);
    }

    @Override
    public ResponseEntity<?> findPostsByIdIn(List<String> ids) {
        List<PostResponse> postResponses = postService.postsByIdsIn(ids);
        return ResponseEntity.ok(postResponses);
    }
}
