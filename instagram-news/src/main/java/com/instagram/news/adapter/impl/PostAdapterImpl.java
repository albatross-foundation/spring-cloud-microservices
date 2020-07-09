package com.instagram.news.adapter.impl;

import com.instagram.news.adapter.PostAdapter;
import com.instagram.news.client.PostServiceClient;
import com.instagram.news.config.JwtConfig;
import com.instagram.news.domain.Post;
import com.instagram.news.exception.UnableToGetPostsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostAdapterImpl implements PostAdapter {

    @Autowired
    private PostServiceClient postServiceClient;
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public List<Post> findPostsIn(String token, List<String> ids) {
        log.info("finding posts for ids {}", ids);

        ResponseEntity<List<Post>> response =
                postServiceClient.findPostsByIdIn(jwtConfig.getPrefix() + token, ids);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new UnableToGetPostsException(
                    String.format("unable to get posts for ids", ids));
        }
    }
}
