package com.instagram.post.service;

import com.instagram.post.domain.PostRequest;
import com.instagram.post.domain.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse createPost(PostRequest request);

    void deletePost(String postId, String userName);

    List<PostResponse> postsByUsername(String userName);

    List<PostResponse> postsByIdsIn(List<String> ids);
}
