package com.instagram.news.adapter;

import com.instagram.news.domain.Post;

import java.util.List;

public interface PostAdapter {
    public List<Post> findPostsIn(String token, List<String> ids);
}
